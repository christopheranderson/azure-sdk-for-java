/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.cognitiveservices.search.newssearch;

import com.microsoft.azure.cognitiveservices.search.newssearch.implementation.BingNewsSearchAPIImpl;
import com.microsoft.rest.RestClient;
import com.microsoft.rest.credentials.ServiceClientCredentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Entry point to Azure Cognitive Services Bing News Search manager.
 */
public class BingNewsSearchManager {
    /**
     * Initializes an instance of Bing News Search API client.
     *
     * @param subscriptionKey the Bing Search API key
     * @return the Bing News Search API client
     */
    public static BingNewsSearchAPI authenticate(String subscriptionKey) {
        return authenticate("https://api.cognitive.microsoft.com/bing/v7.0/", subscriptionKey);
    }

    /**
     * Initializes an instance of Bing News Search API client.
     *
     * @param baseUrl the base URL of the service
     * @param subscriptionKey the Bing Search API key
     * @return the Bing News Search API client
     */
    public static BingNewsSearchAPI authenticate(String baseUrl, final String subscriptionKey) {
        ServiceClientCredentials serviceClientCredentials = new ServiceClientCredentials() {
            @Override
            public void applyCredentialsFilter(OkHttpClient.Builder builder) {
                builder.addNetworkInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = null;
                                Request original = chain.request();
                                // Request customization: add request headers
                                Request.Builder requestBuilder = original.newBuilder()
                                        .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
                                request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        });
            }
        };
        return authenticate(baseUrl, serviceClientCredentials);
    }

    /**
     * Initializes an instance of Bing News Search API client.
     *
     * @param credentials the management credentials for Azure
     * @return the Bing News Search API client
     */
    public static BingNewsSearchAPI authenticate(ServiceClientCredentials credentials) {
        return authenticate("https://api.cognitive.microsoft.com/bing/v7.0/", credentials);
    }

    /**
     * Initializes an instance of Bing News Search API client.
     *
     * @param baseUrl the base URL of the service
     * @param credentials the management credentials for Azure
     * @return the Bing News Search API client
     */
    public static BingNewsSearchAPI authenticate(String baseUrl, ServiceClientCredentials credentials) {
        return new BingNewsSearchAPIImpl(baseUrl, credentials);
    }

    /**
     * Initializes an instance of Bing News Search API client.
     *
     * @param restClient the REST client to connect to Azure.
     * @return the Bing News Search API client
     */
    public static BingNewsSearchAPI authenticate(RestClient restClient) {
        return new BingNewsSearchAPIImpl(restClient);
    }
}
