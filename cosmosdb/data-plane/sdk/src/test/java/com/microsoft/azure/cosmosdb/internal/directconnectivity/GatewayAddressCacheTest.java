/*
 * The MIT License (MIT)
 * Copyright (c) 2018 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.microsoft.azure.cosmosdb.internal.directconnectivity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.microsoft.azure.cosmosdb.ConfigsBuilder;
import com.microsoft.azure.cosmosdb.Database;
import com.microsoft.azure.cosmosdb.Document;
import com.microsoft.azure.cosmosdb.DocumentCollection;
import com.microsoft.azure.cosmosdb.PartitionKeyDefinition;
import com.microsoft.azure.cosmosdb.RequestOptions;
import com.microsoft.azure.cosmosdb.internal.OperationType;
import com.microsoft.azure.cosmosdb.internal.ResourceType;
import com.microsoft.azure.cosmosdb.internal.routing.PartitionKeyRangeIdentity;
import com.microsoft.azure.cosmosdb.rx.AsyncDocumentClient;
import com.microsoft.azure.cosmosdb.rx.AsyncDocumentClient.Builder;
import com.microsoft.azure.cosmosdb.rx.TestConfigurations;
import com.microsoft.azure.cosmosdb.rx.TestSuiteBase;
import com.microsoft.azure.cosmosdb.rx.internal.Configs;
import com.microsoft.azure.cosmosdb.rx.internal.HttpClientFactory;
import com.microsoft.azure.cosmosdb.rx.internal.IAuthorizationTokenProvider;
import com.microsoft.azure.cosmosdb.rx.internal.RxDocumentClientImpl;
import com.microsoft.azure.cosmosdb.rx.internal.RxDocumentServiceRequest;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.client.CompositeHttpClient;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import rx.Single;
import rx.observers.TestSubscriber;

import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GatewayAddressCacheTest extends TestSuiteBase {
    private Database createdDatabase;
    private DocumentCollection createdCollection;

    private AsyncDocumentClient client;

    @Factory(dataProvider = "clientBuilders")
    public GatewayAddressCacheTest(Builder clientBuilder) {
        this.clientBuilder = clientBuilder;
    }

    @DataProvider(name = "targetPartitionsKeyRangeListAndCollectionLinkParams")
    public Object[][] partitionsKeyRangeListAndCollectionLinkParams() {
        return new Object[][] {
                // target partition key range ids, collection link
                { ImmutableList.of("0"), getNameBasedCollectionLink(), Protocol.Tcp },
                { ImmutableList.of("0"), getNameBasedCollectionLink(), Protocol.Https },

                { ImmutableList.of("1"), getNameBasedCollectionLink(), Protocol.Https },
                { ImmutableList.of("1"), getCollectionSelfLink(), Protocol.Https },
                { ImmutableList.of("3"), getNameBasedCollectionLink(), Protocol.Https },

                { ImmutableList.of("0", "1"), getNameBasedCollectionLink(), Protocol.Https },
                { ImmutableList.of("1", "3"), getNameBasedCollectionLink(), Protocol.Https },
        };
    }

    @DataProvider(name = "protocolProvider")
    public Object[][] protocolProvider() {
        return new Object[][]{
                { Protocol.Https },
                { Protocol.Tcp },
        };
    }

    @Test(groups = { "direct" }, dataProvider = "targetPartitionsKeyRangeListAndCollectionLinkParams", timeOut = TIMEOUT)
    public void getServerAddressesViaGateway(List<String> partitionKeyRangeIds,
                                             String collectionLink,
                                             Protocol protocol) throws Exception {
        Configs configs = ConfigsBuilder.instance().withProtocol(protocol).build();
        // ask gateway for the addresses
        URL serviceEndpoint = new URL(TestConfigurations.HOST);
        IAuthorizationTokenProvider authorizationTokenProvider = (RxDocumentClientImpl) client;

        GatewayAddressCache cache = new GatewayAddressCache(serviceEndpoint,
                protocol,
                authorizationTokenProvider,
                null,
                getCompositeHttpClient(configs));

        RxDocumentServiceRequest req =
                RxDocumentServiceRequest.create(OperationType.Create, ResourceType.Document,
                        collectionLink + "/docs/",
                getDocumentDefinition(), new HashMap<>());

        Single<List<Address>> addresses = cache.getServerAddressesViaGatewayAsync(
                req, createdCollection.getResourceId(), partitionKeyRangeIds, false);

        PartitionReplicasAddressesValidator validator = new PartitionReplicasAddressesValidator.Builder()
                .withProtocol(protocol)
                .replicasOfPartitions(partitionKeyRangeIds)
                .build();

        validateSuccess(addresses, validator, TIMEOUT);
    }

    @Test(groups = { "direct" }, dataProvider = "protocolProvider", timeOut = TIMEOUT)
    public void getMasterAddressesViaGatewayAsync(Protocol protocol) throws Exception {
        Configs configs = ConfigsBuilder.instance().withProtocol(protocol).build();
        // ask gateway for the addresses
        URL serviceEndpoint = new URL(TestConfigurations.HOST);
        IAuthorizationTokenProvider authorizationTokenProvider = (RxDocumentClientImpl) client;

        GatewayAddressCache cache = new GatewayAddressCache(serviceEndpoint,
                                                            protocol,
                                                            authorizationTokenProvider,
                                                            null,
                                                            getCompositeHttpClient(configs));

        RxDocumentServiceRequest req =
                RxDocumentServiceRequest.create(OperationType.Create, ResourceType.Database,
                        "/dbs",
                        new Database(), new HashMap<>());

        Single<List<Address>> addresses = cache.getMasterAddressesViaGatewayAsync(req, ResourceType.Database,
                null, "/dbs/", false, false, null);

        PartitionReplicasAddressesValidator validator = new PartitionReplicasAddressesValidator.Builder()
                .withProtocol(protocol)
                .replicasOfSamePartition()
                .build();

        validateSuccess(addresses, validator, TIMEOUT);
    }

    @DataProvider(name = "targetPartitionsKeyRangeAndCollectionLinkParams")
    public Object[][] partitionsKeyRangeAndCollectionLinkParams() {
        return new Object[][] {
                // target partition key range ids, collection link, protocol
                { "0", getNameBasedCollectionLink(), Protocol.Tcp },
                { "0", getNameBasedCollectionLink(), Protocol.Https },

                { "1", getNameBasedCollectionLink(), Protocol.Https} ,
                { "1", getCollectionSelfLink(), Protocol.Https },
                { "3", getNameBasedCollectionLink(), Protocol.Https },
        };
    }

    @Test(groups = { "direct" }, dataProvider = "targetPartitionsKeyRangeAndCollectionLinkParams", timeOut = TIMEOUT)
    public void tryGetAddresses_ForDataPartitions(String partitionKeyRangeId, String collectionLink, Protocol protocol) throws Exception {
        Configs configs = ConfigsBuilder.instance().withProtocol(protocol).build();
        URL serviceEndpoint = new URL(TestConfigurations.HOST);
        IAuthorizationTokenProvider authorizationTokenProvider = (RxDocumentClientImpl) client;

        GatewayAddressCache cache = new GatewayAddressCache(serviceEndpoint,
                                                            protocol,
                                                            authorizationTokenProvider,
                                                            null,
                                                            getCompositeHttpClient(configs));

        RxDocumentServiceRequest req =
                RxDocumentServiceRequest.create(OperationType.Create, ResourceType.Document,
                        collectionLink,
                       new Database(), new HashMap<>());

        String collectionRid = createdCollection.getResourceId();

        PartitionKeyRangeIdentity partitionKeyRangeIdentity = new PartitionKeyRangeIdentity(collectionRid, partitionKeyRangeId);
        boolean forceRefreshPartitionAddresses = false;
        Single<AddressInformation[]> addressesInfosFromCacheObs = cache.tryGetAddresses(req, partitionKeyRangeIdentity, forceRefreshPartitionAddresses);

        ArrayList<AddressInformation> addressInfosFromCache = Lists.newArrayList(getSuccessResult(addressesInfosFromCacheObs, TIMEOUT));

        Single<List<Address>> masterAddressFromGatewayObs = cache.getServerAddressesViaGatewayAsync(req,
                collectionRid, ImmutableList.of(partitionKeyRangeId), false);
        List<Address> expectedAddresses = getSuccessResult(masterAddressFromGatewayObs, TIMEOUT);

        assertSameAs(addressInfosFromCache, expectedAddresses);
    }

    @DataProvider(name = "openAsyncTargetAndTargetPartitionsKeyRangeAndCollectionLinkParams")
    public Object[][] openAsyncTargetAndPartitionsKeyRangeTargetAndCollectionLinkParams() {
        return new Object[][] {
                // openAsync target partition key range ids, target partition key range id, collection link
                { ImmutableList.of("0", "1"), "0", getNameBasedCollectionLink() },
                { ImmutableList.of("0", "1"), "1", getNameBasedCollectionLink() },
                { ImmutableList.of("0", "1"), "1", getCollectionSelfLink() },
        };
    }

    @Test(groups = { "direct" },
            dataProvider = "openAsyncTargetAndTargetPartitionsKeyRangeAndCollectionLinkParams",
            timeOut = TIMEOUT)
    public void tryGetAddresses_ForDataPartitions_AddressCachedByOpenAsync_NoHttpRequest(
            List<String> allPartitionKeyRangeIds,
            String partitionKeyRangeId, String collectionLink) throws Exception {
        Configs configs = new Configs();
        HttpClientUnderTestWrapper httpClientWrapper = getHttpClientUnderTestWrapper(configs);

        URL serviceEndpoint = new URL(TestConfigurations.HOST);
        IAuthorizationTokenProvider authorizationTokenProvider = (RxDocumentClientImpl) client;

        GatewayAddressCache cache = new GatewayAddressCache(serviceEndpoint,
                                                            Protocol.Https,
                                                            authorizationTokenProvider,
                                                            null,
                                                            httpClientWrapper.getSpyHttpClient());

        String collectionRid = createdCollection.getResourceId();

        List<PartitionKeyRangeIdentity> pkriList = allPartitionKeyRangeIds.stream().map(
                pkri -> new PartitionKeyRangeIdentity(collectionRid, pkri)).collect(Collectors.toList());

        cache.openAsync(createdCollection, pkriList).await();

        assertThat(httpClientWrapper.capturedRequest).asList().hasSize(1);
        httpClientWrapper.capturedRequest.clear();

        RxDocumentServiceRequest req =
                RxDocumentServiceRequest.create(OperationType.Create, ResourceType.Document,
                        collectionLink,
                        new Database(), new HashMap<>());

        PartitionKeyRangeIdentity partitionKeyRangeIdentity = new PartitionKeyRangeIdentity(collectionRid, partitionKeyRangeId);
        boolean forceRefreshPartitionAddresses = false;
        Single<AddressInformation[]> addressesInfosFromCacheObs = cache.tryGetAddresses(req, partitionKeyRangeIdentity, forceRefreshPartitionAddresses);
        ArrayList<AddressInformation> addressInfosFromCache = Lists.newArrayList(getSuccessResult(addressesInfosFromCacheObs, TIMEOUT));

        // no new request is made
        assertThat(httpClientWrapper.capturedRequest)
                .describedAs("no http request: addresses already cached by openAsync")
                .asList().hasSize(0);

        Single<List<Address>> masterAddressFromGatewayObs = cache.getServerAddressesViaGatewayAsync(req,
                collectionRid, ImmutableList.of(partitionKeyRangeId), false);
        List<Address> expectedAddresses = getSuccessResult(masterAddressFromGatewayObs, TIMEOUT);

        assertThat(httpClientWrapper.capturedRequest)
                .describedAs("getServerAddressesViaGatewayAsync will read addresses from gateway")
                .asList().hasSize(1);

        assertSameAs(addressInfosFromCache, expectedAddresses);
    }

    @Test(groups = { "direct" },
            dataProvider = "openAsyncTargetAndTargetPartitionsKeyRangeAndCollectionLinkParams",
            timeOut = TIMEOUT)
    public void tryGetAddresses_ForDataPartitions_ForceRefresh(
            List<String> allPartitionKeyRangeIds,
            String partitionKeyRangeId,
            String collectionLink) throws Exception {
        Configs configs = new Configs();
        HttpClientUnderTestWrapper httpClientWrapper = getHttpClientUnderTestWrapper(configs);

        URL serviceEndpoint = new URL(TestConfigurations.HOST);
        IAuthorizationTokenProvider authorizationTokenProvider = (RxDocumentClientImpl) client;

        GatewayAddressCache cache = new GatewayAddressCache(serviceEndpoint,
                                                            Protocol.Https,
                                                            authorizationTokenProvider,
                                                            null,
                                                            httpClientWrapper.getSpyHttpClient());

        String collectionRid = createdCollection.getResourceId();

        List<PartitionKeyRangeIdentity> pkriList = allPartitionKeyRangeIds.stream().map(
                pkri -> new PartitionKeyRangeIdentity(collectionRid, pkri)).collect(Collectors.toList());

        cache.openAsync(createdCollection, pkriList).await();

        assertThat(httpClientWrapper.capturedRequest).asList().hasSize(1);
        httpClientWrapper.capturedRequest.clear();

        RxDocumentServiceRequest req =
                RxDocumentServiceRequest.create(OperationType.Create, ResourceType.Document,
                        collectionLink,
                        new Database(), new HashMap<>());

        PartitionKeyRangeIdentity partitionKeyRangeIdentity = new PartitionKeyRangeIdentity(collectionRid, partitionKeyRangeId);
        Single<AddressInformation[]> addressesInfosFromCacheObs = cache.tryGetAddresses(req, partitionKeyRangeIdentity, true);
        ArrayList<AddressInformation> addressInfosFromCache = Lists.newArrayList(getSuccessResult(addressesInfosFromCacheObs, TIMEOUT));

        // no new request is made
        assertThat(httpClientWrapper.capturedRequest)
                .describedAs("force refresh fetched from gateway")
                .asList().hasSize(1);

        Single<List<Address>> masterAddressFromGatewayObs = cache.getServerAddressesViaGatewayAsync(req,
                collectionRid, ImmutableList.of(partitionKeyRangeId), false);
        List<Address> expectedAddresses = getSuccessResult(masterAddressFromGatewayObs, TIMEOUT);

        assertThat(httpClientWrapper.capturedRequest)
                .describedAs("getServerAddressesViaGatewayAsync will read addresses from gateway")
                .asList().hasSize(2);

        assertSameAs(addressInfosFromCache, expectedAddresses);
    }

    @Test(groups = { "direct" },
            dataProvider = "openAsyncTargetAndTargetPartitionsKeyRangeAndCollectionLinkParams",
            timeOut = TIMEOUT)
    public void tryGetAddresses_ForDataPartitions_Suboptimal_Refresh(
            List<String> allPartitionKeyRangeIds,
            String partitionKeyRangeId,
            String collectionLink) throws Exception {
        Configs configs = new Configs();
        HttpClientUnderTestWrapper httpClientWrapper = getHttpClientUnderTestWrapper(configs);

        URL serviceEndpoint = new URL(TestConfigurations.HOST);
        IAuthorizationTokenProvider authorizationTokenProvider = (RxDocumentClientImpl) client;

        int suboptimalRefreshTime = 2;

        GatewayAddressCache origCache = new GatewayAddressCache(serviceEndpoint,
                                                                Protocol.Https,
                                                                authorizationTokenProvider,
                                                                null,
                                                                httpClientWrapper.getSpyHttpClient(),
                                                                suboptimalRefreshTime);

        String collectionRid = createdCollection.getResourceId();

        List<PartitionKeyRangeIdentity> pkriList = allPartitionKeyRangeIds.stream().map(
                pkri -> new PartitionKeyRangeIdentity(collectionRid, pkri)).collect(Collectors.toList());

        origCache.openAsync(createdCollection, pkriList).await();

        assertThat(httpClientWrapper.capturedRequest).asList().hasSize(1);
        httpClientWrapper.capturedRequest.clear();

        RxDocumentServiceRequest req =
                RxDocumentServiceRequest.create(OperationType.Create, ResourceType.Document,
                        collectionLink,
                        new Database(), new HashMap<>());

        PartitionKeyRangeIdentity partitionKeyRangeIdentity = new PartitionKeyRangeIdentity(collectionRid, partitionKeyRangeId);
        Single<AddressInformation[]> addressesInfosFromCacheObs = origCache.tryGetAddresses(req, partitionKeyRangeIdentity, true);
        ArrayList<AddressInformation> addressInfosFromCache = Lists.newArrayList(getSuccessResult(addressesInfosFromCacheObs, TIMEOUT));

        // no new request is made
        assertThat(httpClientWrapper.capturedRequest)
                .describedAs("force refresh fetched from gateway")
                .asList().hasSize(1);

        GatewayAddressCache spyCache = Mockito.spy(origCache);

        final AtomicInteger fetchCounter = new AtomicInteger(0);
        Mockito.doAnswer(new Answer() {
            @Override
            public Single<List<Address>> answer(InvocationOnMock invocationOnMock) throws Throwable {

                RxDocumentServiceRequest req = invocationOnMock.getArgumentAt(0, RxDocumentServiceRequest.class);
                String collectionRid = invocationOnMock.getArgumentAt(1, String.class);
                List partitionKeyRangeIds = invocationOnMock.getArgumentAt(2, List.class);
                boolean forceRefresh = invocationOnMock.getArgumentAt(3, Boolean.class);

                int cnt = fetchCounter.getAndIncrement();

                if (cnt == 0) {
                    Single<List<Address>> res = origCache.getServerAddressesViaGatewayAsync(req,
                            collectionRid,
                            partitionKeyRangeIds,
                            forceRefresh);

                    // remove one replica
                    return res.map(list -> removeOneReplica(list));
                }

                return origCache.getServerAddressesViaGatewayAsync(req,
                        collectionRid,
                        partitionKeyRangeIds,
                        forceRefresh);
            }
        }).when(spyCache).getServerAddressesViaGatewayAsync(Matchers.any(RxDocumentServiceRequest.class), Matchers.anyString(),
                Matchers.anyList(), Matchers.anyBoolean());

        httpClientWrapper.capturedRequest.clear();

        // force refresh to replace existing with sub-optimal addresses
        addressesInfosFromCacheObs = spyCache.tryGetAddresses(req, partitionKeyRangeIdentity, true);
        AddressInformation[] suboptimalAddresses = getSuccessResult(addressesInfosFromCacheObs, TIMEOUT);
        assertThat(httpClientWrapper.capturedRequest)
                .describedAs("getServerAddressesViaGatewayAsync will read addresses from gateway")
                .asList().hasSize(1);
        httpClientWrapper.capturedRequest.clear();
        assertThat(suboptimalAddresses).hasSize(ServiceConfig.SystemReplicationPolicy.MaxReplicaSetSize - 1);
        assertThat(fetchCounter.get()).isEqualTo(1);

        // no refresh, use cache
        addressesInfosFromCacheObs = spyCache.tryGetAddresses(req, partitionKeyRangeIdentity, false);
        suboptimalAddresses = getSuccessResult(addressesInfosFromCacheObs, TIMEOUT);
        assertThat(httpClientWrapper.capturedRequest)
                .describedAs("getServerAddressesViaGatewayAsync will read addresses from gateway")
                .asList().hasSize(0);
        assertThat(suboptimalAddresses).hasSize(ServiceConfig.SystemReplicationPolicy.MaxReplicaSetSize - 1);
        assertThat(fetchCounter.get()).isEqualTo(1);

        // wait for refresh time
        TimeUnit.SECONDS.sleep(suboptimalRefreshTime + 1);

        addressesInfosFromCacheObs = spyCache.tryGetAddresses(req, partitionKeyRangeIdentity, false);
        AddressInformation[] addresses = getSuccessResult(addressesInfosFromCacheObs, TIMEOUT);
        assertThat(addresses).hasSize(ServiceConfig.SystemReplicationPolicy.MaxReplicaSetSize);
        assertThat(httpClientWrapper.capturedRequest)
                .describedAs("getServerAddressesViaGatewayAsync will read addresses from gateway")
                .asList().hasSize(1);
        assertThat(fetchCounter.get()).isEqualTo(2);
    }

    @Test(groups = { "direct" }, dataProvider = "protocolProvider",timeOut = TIMEOUT)
    public void tryGetAddresses_ForMasterPartition(Protocol protocol) throws Exception {
        Configs configs = ConfigsBuilder.instance().withProtocol(protocol).build();
        URL serviceEndpoint = new URL(TestConfigurations.HOST);
        IAuthorizationTokenProvider authorizationTokenProvider = (RxDocumentClientImpl) client;

        GatewayAddressCache cache = new GatewayAddressCache(serviceEndpoint,
                                                            protocol,
                                                            authorizationTokenProvider,
                                                            null,
                                                            getCompositeHttpClient(configs));

        RxDocumentServiceRequest req =
                RxDocumentServiceRequest.create(OperationType.Create, ResourceType.Database,
                        "/dbs",
                        new Database(), new HashMap<>());

        PartitionKeyRangeIdentity partitionKeyRangeIdentity = new PartitionKeyRangeIdentity("M");
        boolean forceRefreshPartitionAddresses = false;
        Single<AddressInformation[]> addressesInfosFromCacheObs = cache.tryGetAddresses(req, partitionKeyRangeIdentity, forceRefreshPartitionAddresses);

        ArrayList<AddressInformation> addressInfosFromCache = Lists.newArrayList(getSuccessResult(addressesInfosFromCacheObs, TIMEOUT));

        Single<List<Address>> masterAddressFromGatewayObs = cache.getMasterAddressesViaGatewayAsync(req, ResourceType.Database,
                null, "/dbs/", false, false, null);
        List<Address> expectedAddresses = getSuccessResult(masterAddressFromGatewayObs, TIMEOUT);

        assertSameAs(addressInfosFromCache, expectedAddresses);
    }

    @DataProvider(name = "refreshTime")
    public Object[][] refreshTime() {
        return new Object[][] {
                // refresh time, wait before doing tryGetAddresses
                { 60, 1 },
                { 1, 2 },
        };
    }

    @Test(groups = { "direct" }, timeOut = TIMEOUT, dataProvider = "refreshTime")
    public void tryGetAddresses_ForMasterPartition_MasterPartitionAddressAlreadyCached_NoNewHttpRequest(
            int suboptimalPartitionForceRefreshIntervalInSeconds,
            int waitTimeInBetweenAttemptsInSeconds
            ) throws Exception {
        Configs configs = new Configs();
        HttpClientUnderTestWrapper clientWrapper = getHttpClientUnderTestWrapper(configs);

        URL serviceEndpoint = new URL(TestConfigurations.HOST);
        IAuthorizationTokenProvider authorizationTokenProvider = (RxDocumentClientImpl) client;


        GatewayAddressCache cache = new GatewayAddressCache(serviceEndpoint,
                                                            Protocol.Https,
                                                            authorizationTokenProvider,
                                                            null,
                                                            clientWrapper.getSpyHttpClient(),
                                                            suboptimalPartitionForceRefreshIntervalInSeconds);

        RxDocumentServiceRequest req =
                RxDocumentServiceRequest.create(OperationType.Create, ResourceType.Database,
                        "/dbs",
                        new Database(), new HashMap<>());

        PartitionKeyRangeIdentity partitionKeyRangeIdentity = new PartitionKeyRangeIdentity("M");
        boolean forceRefreshPartitionAddresses = false;

        // request master partition info to ensure it is cached.
        AddressInformation[] expectedAddresses = cache.tryGetAddresses(req,
                partitionKeyRangeIdentity,
                forceRefreshPartitionAddresses)
                .toBlocking().value();

        assertThat(clientWrapper.capturedRequest).asList().hasSize(1);
        clientWrapper.capturedRequest.clear();


        TimeUnit.SECONDS.sleep(waitTimeInBetweenAttemptsInSeconds);

        Single<AddressInformation[]> addressesObs = cache.tryGetAddresses(req,
                partitionKeyRangeIdentity,
                forceRefreshPartitionAddresses);

        AddressInformation[] actualAddresses = getSuccessResult(addressesObs, TIMEOUT);

        assertExactlyEqual(actualAddresses, expectedAddresses);

        // the cache address is used. no new http request is sent
        assertThat(clientWrapper.capturedRequest).asList().hasSize(0);
    }

    @Test(groups = { "direct" }, timeOut = TIMEOUT)
    public void tryGetAddresses_ForMasterPartition_ForceRefresh() throws Exception {
        Configs configs = new Configs();
        HttpClientUnderTestWrapper clientWrapper = getHttpClientUnderTestWrapper(configs);

        URL serviceEndpoint = new URL(TestConfigurations.HOST);
        IAuthorizationTokenProvider authorizationTokenProvider = (RxDocumentClientImpl) client;

        GatewayAddressCache cache = new GatewayAddressCache(serviceEndpoint,
                                                            Protocol.Https,
                                                            authorizationTokenProvider,
                                                            null,
                                                            clientWrapper.getSpyHttpClient());

        RxDocumentServiceRequest req =
                RxDocumentServiceRequest.create(OperationType.Create, ResourceType.Database,
                        "/dbs",
                        new Database(), new HashMap<>());

        PartitionKeyRangeIdentity partitionKeyRangeIdentity = new PartitionKeyRangeIdentity("M");

        // request master partition info to ensure it is cached.
        AddressInformation[] expectedAddresses = cache.tryGetAddresses(req,
                partitionKeyRangeIdentity,
                false)
                .toBlocking().value();

        assertThat(clientWrapper.capturedRequest).asList().hasSize(1);
        clientWrapper.capturedRequest.clear();

        Single<AddressInformation[]> addressesObs = cache.tryGetAddresses(req,
                partitionKeyRangeIdentity,
                true);

        AddressInformation[] actualAddresses = getSuccessResult(addressesObs, TIMEOUT);

        assertExactlyEqual(actualAddresses, expectedAddresses);

        // the cache address is used. no new http request is sent
        assertThat(clientWrapper.capturedRequest).asList().hasSize(1);
    }

    private static List<Address> removeOneReplica(List<Address> addresses) {
        addresses.remove(0);
        return addresses;
    }

    @Test(groups = { "direct" }, timeOut = TIMEOUT)
    public void tryGetAddresses_SuboptimalMasterPartition_NotStaleEnough_NoRefresh() throws Exception {
        Configs configs = new Configs();
        Instant start = Instant.now();
        HttpClientUnderTestWrapper clientWrapper = getHttpClientUnderTestWrapper(configs);

        URL serviceEndpoint = new URL(TestConfigurations.HOST);
        IAuthorizationTokenProvider authorizationTokenProvider = (RxDocumentClientImpl) client;

        int refreshPeriodInSeconds = 10;

        GatewayAddressCache origCache = new GatewayAddressCache(serviceEndpoint,
                                                                Protocol.Https,
                                                                authorizationTokenProvider,
                                                                null,
                                                                clientWrapper.getSpyHttpClient(), refreshPeriodInSeconds);

        GatewayAddressCache spyCache = Mockito.spy(origCache);

        final AtomicInteger getMasterAddressesViaGatewayAsyncInvocation = new AtomicInteger(0);
        Mockito.doAnswer(new Answer() {
            @Override
            public Single<List<Address>> answer(InvocationOnMock invocationOnMock) throws Throwable {

                RxDocumentServiceRequest request = invocationOnMock.getArgumentAt(0, RxDocumentServiceRequest.class);
                ResourceType resourceType = invocationOnMock.getArgumentAt(1, ResourceType.class);
                String resourceAddress = invocationOnMock.getArgumentAt(2, String.class);
                String entryUrl = invocationOnMock.getArgumentAt(3, String.class);
                boolean forceRefresh = invocationOnMock.getArgumentAt(4, Boolean.class);
                boolean useMasterCollectionResolver = invocationOnMock.getArgumentAt(5, Boolean.class);

                int cnt = getMasterAddressesViaGatewayAsyncInvocation.getAndIncrement();

                if (cnt == 0) {
                    Single<List<Address>> res = origCache.getMasterAddressesViaGatewayAsync(
                            request,
                            resourceType,
                            resourceAddress,
                            entryUrl,
                            forceRefresh,
                            useMasterCollectionResolver,
                            null);

                    // remove one replica
                    return res.map(list -> removeOneReplica(list));
                }

                return origCache.getMasterAddressesViaGatewayAsync(
                        request,
                        resourceType,
                        resourceAddress,
                        entryUrl,
                        forceRefresh,
                        useMasterCollectionResolver,
                        null);
                }
            }).when(spyCache).getMasterAddressesViaGatewayAsync(Matchers.any(RxDocumentServiceRequest.class), Matchers.any(ResourceType.class), Matchers.anyString(),
                Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyBoolean(), Matchers.anyMap());


        RxDocumentServiceRequest req =
                RxDocumentServiceRequest.create(OperationType.Create, ResourceType.Database,
                        "/dbs",
                        new Database(), new HashMap<>());

        PartitionKeyRangeIdentity partitionKeyRangeIdentity = new PartitionKeyRangeIdentity("M");

        // request master partition info to ensure it is cached.
        AddressInformation[] expectedAddresses = spyCache.tryGetAddresses(req,
                partitionKeyRangeIdentity,
                false)
                .toBlocking().value();

        assertThat(clientWrapper.capturedRequest).asList().hasSize(1);
        clientWrapper.capturedRequest.clear();

        Single<AddressInformation[]> addressesObs = spyCache.tryGetAddresses(req,
                partitionKeyRangeIdentity,
                false);

        AddressInformation[] actualAddresses = getSuccessResult(addressesObs, TIMEOUT);

        assertExactlyEqual(actualAddresses, expectedAddresses);

        // the cache address is used. no new http request is sent
        assertThat(clientWrapper.capturedRequest).asList().hasSize(0);

        Instant end = Instant.now();
        assertThat(end.minusSeconds(refreshPeriodInSeconds)).isBefore(start);
    }

    @Test(groups = { "direct" }, timeOut = TIMEOUT)
    public void tryGetAddresses_SuboptimalMasterPartition_Stale_DoRefresh() throws Exception {
        Configs configs = new Configs();
        HttpClientUnderTestWrapper clientWrapper = getHttpClientUnderTestWrapper(configs);

        URL serviceEndpoint = new URL(TestConfigurations.HOST);
        IAuthorizationTokenProvider authorizationTokenProvider = (RxDocumentClientImpl) client;

        int refreshPeriodInSeconds = 1;

        GatewayAddressCache origCache = new GatewayAddressCache(serviceEndpoint,
                                                                Protocol.Https,
                                                                authorizationTokenProvider,
                                                                null,
                                                                clientWrapper.getSpyHttpClient(), refreshPeriodInSeconds);

        GatewayAddressCache spyCache = Mockito.spy(origCache);

        final AtomicInteger getMasterAddressesViaGatewayAsyncInvocation = new AtomicInteger(0);
        Mockito.doAnswer(new Answer() {
            @Override
            public Single<List<Address>> answer(InvocationOnMock invocationOnMock) throws Throwable {

                System.out.print("fetch");

                RxDocumentServiceRequest request = invocationOnMock.getArgumentAt(0, RxDocumentServiceRequest.class);
                ResourceType resourceType = invocationOnMock.getArgumentAt(1, ResourceType.class);
                String resourceAddress = invocationOnMock.getArgumentAt(2, String.class);
                String entryUrl = invocationOnMock.getArgumentAt(3, String.class);
                boolean forceRefresh = invocationOnMock.getArgumentAt(4, Boolean.class);
                boolean useMasterCollectionResolver = invocationOnMock.getArgumentAt(5, Boolean.class);

                int cnt = getMasterAddressesViaGatewayAsyncInvocation.getAndIncrement();

                if (cnt == 0) {
                    Single<List<Address>> res = origCache.getMasterAddressesViaGatewayAsync(
                            request,
                            resourceType,
                            resourceAddress,
                            entryUrl,
                            forceRefresh,
                            useMasterCollectionResolver,
                            null);

                    // remove one replica
                    return res.map(list -> removeOneReplica(list));
                }

                return origCache.getMasterAddressesViaGatewayAsync(
                        request,
                        resourceType,
                        resourceAddress,
                        entryUrl,
                        forceRefresh,
                        useMasterCollectionResolver,
                        null);
            }
        }).when(spyCache).getMasterAddressesViaGatewayAsync(Matchers.any(RxDocumentServiceRequest.class), Matchers.any(ResourceType.class), Matchers.anyString(),
                Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyBoolean(), Matchers.anyMap());

        RxDocumentServiceRequest req =
                RxDocumentServiceRequest.create(OperationType.Create, ResourceType.Database,
                        "/dbs",
                        new Database(), new HashMap<>());

        PartitionKeyRangeIdentity partitionKeyRangeIdentity = new PartitionKeyRangeIdentity("M");

        // request master partition info to ensure it is cached.
        AddressInformation[] subOptimalAddresses = spyCache.tryGetAddresses(req,
                partitionKeyRangeIdentity,
                false)
                .toBlocking().value();

        assertThat(getMasterAddressesViaGatewayAsyncInvocation.get()).isEqualTo(1);
        assertThat(subOptimalAddresses).hasSize(ServiceConfig.SystemReplicationPolicy.MaxReplicaSetSize - 1);

        Instant start = Instant.now();
        TimeUnit.SECONDS.sleep(refreshPeriodInSeconds + 1);
        Instant end = Instant.now();
        assertThat(end.minusSeconds(refreshPeriodInSeconds)).isAfter(start);

        assertThat(clientWrapper.capturedRequest).asList().hasSize(1);
        clientWrapper.capturedRequest.clear();

        Single<AddressInformation[]> addressesObs = spyCache.tryGetAddresses(req,
                partitionKeyRangeIdentity,
                false);


        AddressInformation[] actualAddresses = getSuccessResult(addressesObs, TIMEOUT);
        // the cache address is used. no new http request is sent
        assertThat(clientWrapper.capturedRequest).asList().hasSize(1);
        assertThat(getMasterAddressesViaGatewayAsyncInvocation.get()).isEqualTo(2);
        assertThat(actualAddresses).hasSize(ServiceConfig.SystemReplicationPolicy.MaxReplicaSetSize);

        List<Address> fetchedAddresses = origCache.getMasterAddressesViaGatewayAsync(req, ResourceType.Database,
                null, "/dbs/", false, false, null).toBlocking().value();

        assertSameAs(ImmutableList.copyOf(actualAddresses),  fetchedAddresses);
    }

    public static void assertSameAs(List<AddressInformation> actual, List<Address> expected) {
        assertThat(actual).asList().hasSize(expected.size());
        for(int i = 0; i < expected.size(); i++) {
            assertEqual(actual.get(i), expected.get(i));
        }
    }

    private static void assertEqual(AddressInformation actual, Address expected) {
        assertThat(actual.getPhysicalUri()).isEqualTo(expected.getPhyicalUri());
        assertThat(actual.getProtocolScheme()).isEqualTo(expected.getProtocolScheme().toLowerCase());
        assertThat(actual.isPrimary()).isEqualTo(expected.IsPrimary());
    }

    private static void assertEqual(AddressInformation actual, AddressInformation expected) {
        assertThat(actual.getPhysicalUri()).isEqualTo(expected.getPhysicalUri());
        assertThat(actual.getProtocolName()).isEqualTo(expected.getProtocolName());
        assertThat(actual.isPrimary()).isEqualTo(expected.isPrimary());
        assertThat(actual.isPublic()).isEqualTo(expected.isPublic());
    }

    public static void assertExactlyEqual(AddressInformation[] actual, AddressInformation[] expected) {
        assertExactlyEqual(Arrays.asList(actual), Arrays.asList(expected));
    }

    public static void assertExactlyEqual(List<AddressInformation> actual, List<AddressInformation> expected) {
        assertThat(actual).asList().hasSize(expected.size());
        for(int i = 0; i < expected.size(); i++) {
            assertEqual(actual.get(i), expected.get(i));
        }
    }

    public static<T> T getSuccessResult(Single<T> observable, long timeout) {
        TestSubscriber<T> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(timeout, TimeUnit.MILLISECONDS);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertValueCount(1);
        return testSubscriber.getOnNextEvents().get(0);
    }

    public static void validateSuccess(Single<List<Address>> observable,
                                       PartitionReplicasAddressesValidator validator, long timeout) {
        TestSubscriber<List<Address>> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(timeout, TimeUnit.MILLISECONDS);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertValueCount(1);
        validator.validate(testSubscriber.getOnNextEvents().get(0));
    }
    
    @BeforeClass(groups = { "direct" }, timeOut = SETUP_TIMEOUT)
    public void beforeClass() {
        client = clientBuilder.build();
        createdDatabase = SHARED_DATABASE;

        RequestOptions options = new RequestOptions();
        options.setOfferThroughput(30000);
        createdCollection = createCollection(client, createdDatabase.getId(), getCollectionDefinition(), options);
    }

    @AfterClass(groups = { "direct" }, timeOut = SHUTDOWN_TIMEOUT, alwaysRun = true)
    public void afterClass() {
        safeDeleteCollection(client, createdCollection);
        safeClose(client);
    }

    static protected DocumentCollection getCollectionDefinition() {
        PartitionKeyDefinition partitionKeyDef = new PartitionKeyDefinition();
        ArrayList<String> paths = new ArrayList<>();
        paths.add("/mypk");
        partitionKeyDef.setPaths(paths);

        DocumentCollection collectionDefinition = new DocumentCollection();
        collectionDefinition.setId("mycol");
        collectionDefinition.setPartitionKey(partitionKeyDef);

        return collectionDefinition;
    }

    private CompositeHttpClient getCompositeHttpClient(Configs configs) {
        CompositeHttpClient<ByteBuf, ByteBuf> httpClient = new HttpClientFactory(configs)
                .toHttpClientBuilder().build();
        return httpClient;
    }

    private HttpClientUnderTestWrapper getHttpClientUnderTestWrapper(Configs configs) {
        CompositeHttpClient<ByteBuf, ByteBuf> origHttpClient = getCompositeHttpClient(configs);
        return new HttpClientUnderTestWrapper(origHttpClient);
    }

    public String getNameBasedCollectionLink() {
        return "dbs/" + createdDatabase.getId() + "/colls/" + createdCollection.getId();
    }

    public String getCollectionSelfLink() {
        return createdCollection.getSelfLink();
    }

    private Document getDocumentDefinition() {
        String uuid = UUID.randomUUID().toString();
        Document doc = new Document(String.format("{ "
                + "\"id\": \"%s\", "
                + "\"mypk\": \"%s\", "
                + "\"sgmts\": [[6519456, 1471916863], [2498434, 1455671440]]"
                + "}"
                , uuid, uuid));
        return doc;
    }
}
