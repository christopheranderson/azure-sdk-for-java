/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.apimanagement.v2018_06_01_preview;

import com.microsoft.azure.arm.collection.SupportsCreating;
import rx.Completable;
import rx.Observable;
import com.microsoft.azure.management.apimanagement.v2018_06_01_preview.implementation.OpenIdConnectProvidersInner;
import com.microsoft.azure.arm.model.HasInner;

/**
 * Type representing OpenIdConnectProviders.
 */
public interface OpenIdConnectProviders extends SupportsCreating<OpenidConnectProviderContract.DefinitionStages.Blank>, HasInner<OpenIdConnectProvidersInner> {
    /**
     * Lists of all the OpenId Connect Providers.
     *
     * @param resourceGroupName The name of the resource group.
     * @param serviceName The name of the API Management service.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<OpenidConnectProviderContract> listByServiceAsync(final String resourceGroupName, final String serviceName);

    /**
     * Gets the entity state (Etag) version of the openIdConnectProvider specified by its identifier.
     *
     * @param resourceGroupName The name of the resource group.
     * @param serviceName The name of the API Management service.
     * @param opid Identifier of the OpenID Connect Provider.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Completable getEntityTagAsync(String resourceGroupName, String serviceName, String opid);

    /**
     * Gets specific OpenID Connect Provider.
     *
     * @param resourceGroupName The name of the resource group.
     * @param serviceName The name of the API Management service.
     * @param opid Identifier of the OpenID Connect Provider.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<OpenidConnectProviderContract> getAsync(String resourceGroupName, String serviceName, String opid);

    /**
     * Deletes specific OpenID Connect Provider of the API Management service instance.
     *
     * @param resourceGroupName The name of the resource group.
     * @param serviceName The name of the API Management service.
     * @param opid Identifier of the OpenID Connect Provider.
     * @param ifMatch ETag of the Entity. ETag should match the current entity state from the header response of the GET request or it should be * for unconditional update.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Completable deleteAsync(String resourceGroupName, String serviceName, String opid, String ifMatch);

}
