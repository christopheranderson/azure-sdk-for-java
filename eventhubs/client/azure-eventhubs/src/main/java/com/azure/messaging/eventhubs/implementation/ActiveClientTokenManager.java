// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.messaging.eventhubs.implementation;

import com.azure.core.amqp.CBSNode;
import com.azure.core.amqp.exception.AmqpException;
import com.azure.core.amqp.exception.AmqpResponseCode;
import com.azure.core.exception.AzureException;
import com.azure.core.util.logging.ClientLogger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.io.Closeable;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Manages the re-authorization of the client to the token audience against the CBS node.
 */
class ActiveClientTokenManager implements Closeable {
    private final ClientLogger logger = new ClientLogger(ActiveClientTokenManager.class);
    private final AtomicBoolean hasScheduled = new AtomicBoolean();
    private final AtomicBoolean hasDisposed = new AtomicBoolean();
    private final Mono<CBSNode> cbsNode;
    private final String tokenAudience;
    private final Timer timer;
    private final Flux<AmqpResponseCode> authorizationResults;
    private FluxSink<AmqpResponseCode> sink;

    // last refresh interval in milliseconds.
    private AtomicLong lastRefreshInterval = new AtomicLong();

    ActiveClientTokenManager(Mono<CBSNode> cbsNode, String tokenAudience) {
        this.timer = new Timer(tokenAudience + "-tokenManager");
        this.cbsNode = cbsNode;
        this.tokenAudience = tokenAudience;
        this.authorizationResults = Flux.create(sink -> {
            if (hasDisposed.get()) {
                sink.complete();
            } else {
                this.sink = sink;
            }
        });

        lastRefreshInterval.set(Duration.ofMinutes(1).getSeconds() * 1000);
    }

    /**
     * Gets a flux of the periodic authorization results from the CBS node. Errors are returned on the Flux if
     * authorization is unsuccessful.
     *
     * @return A Flux of authorization results from the CBS node.
     */
    Flux<AmqpResponseCode> getAuthorizationResults() {
        return authorizationResults;
    }

    /**
     * Invokes an authorization call on the CBS node.
     */
    Mono<Void> authorize() {
        if (hasDisposed.get()) {
            return Mono.error(new AzureException("Cannot authorize with CBS node when this token manager has been disposed of."));
        }

        return cbsNode.flatMap(cbsNode -> cbsNode.authorize(tokenAudience))
            .map(expiresOn -> {
                if (!hasScheduled.getAndSet(true)) {
                    logger.asInfo().log("Scheduling refresh token.");
                    Duration between = Duration.between(OffsetDateTime.now(ZoneOffset.UTC), expiresOn);

                    // We want to refresh the token when 90% of the time before expiry has elapsed.
                    long refreshSeconds = (long) Math.floor(between.getSeconds() * 0.9);
                    long refreshIntervalMS = refreshSeconds * 1000;

                    lastRefreshInterval.set(refreshIntervalMS);

                    // This converts it to milliseconds
                    try {
                        this.timer.schedule(new RefreshAuthorizationToken(), refreshIntervalMS);
                    } catch (IllegalStateException e) {
                        logger.asWarning().log("Unable to reschedule timer task.", e);
                        hasScheduled.set(false);
                    }
                }

                return expiresOn;
            }).then();
    }

    @Override
    public void close() {
        if (!hasDisposed.getAndSet(true)) {
            if (this.sink != null) {
                this.sink.complete();
            }

            this.timer.cancel();
        }
    }

    private class RefreshAuthorizationToken extends TimerTask {
        @Override
        public void run() {
            logger.asInfo().log("Refreshing authorization token.");
            authorize().subscribe(
                (Void response) -> {
                    logger.asInfo().log("Response acquired.");
                }, error -> {
                    if ((error instanceof AmqpException) && ((AmqpException) error).isTransient()) {
                        logger.asError().log("Error is transient. Rescheduling authorization task.", error);
                        timer.schedule(new RefreshAuthorizationToken(), lastRefreshInterval.get());
                    } else {
                        logger.asError().log("Error occurred while refreshing token that is not retriable. Not scheduling"
                            + " refresh task. Use ActiveClientTokenManager.authorize() to schedule task again.", error);
                        hasScheduled.set(false);
                    }

                    sink.error(error);
                }, () -> {
                    if (hasDisposed.get()) {
                        logger.asInfo().log("Token manager has been disposed of. Not rescheduling.");
                        return;
                    }

                    logger.asInfo().log("Success. Rescheduling refresh authorization task.");
                    sink.next(AmqpResponseCode.ACCEPTED);

                    try {
                        timer.schedule(new RefreshAuthorizationToken(), lastRefreshInterval.get());
                    } catch (IllegalStateException e) {
                        logger.asWarning().log("Unable to reschedule timer task.", e);
                        hasScheduled.set(false);
                    }
                });
        }
    }
}
