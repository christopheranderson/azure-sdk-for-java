/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.apimanagement.v2019_01_01.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.azure.ProxyResource;

/**
 * Sign-In settings for the Developer Portal.
 */
@JsonFlatten
public class PortalSigninSettingsInner extends ProxyResource {
    /**
     * Redirect Anonymous users to the Sign-In page.
     */
    @JsonProperty(value = "properties.enabled")
    private Boolean enabled;

    /**
     * Get redirect Anonymous users to the Sign-In page.
     *
     * @return the enabled value
     */
    public Boolean enabled() {
        return this.enabled;
    }

    /**
     * Set redirect Anonymous users to the Sign-In page.
     *
     * @param enabled the enabled value to set
     * @return the PortalSigninSettingsInner object itself.
     */
    public PortalSigninSettingsInner withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

}
