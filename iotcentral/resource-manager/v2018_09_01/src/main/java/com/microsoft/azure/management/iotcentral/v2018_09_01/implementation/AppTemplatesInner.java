/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.iotcentral.v2018_09_01.implementation;

import java.util.List;
import com.microsoft.azure.management.iotcentral.v2018_09_01.AppTemplate;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A list of IoT Central Application Templates with a next link.
 */
public class AppTemplatesInner {
    /**
     * The link used to get the next page of IoT Central description objects.
     */
    @JsonProperty(value = "nextLink")
    private String nextLink;

    /**
     * A list of IoT Central Application Templates.
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<AppTemplate> value;

    /**
     * Get the link used to get the next page of IoT Central description objects.
     *
     * @return the nextLink value
     */
    public String nextLink() {
        return this.nextLink;
    }

    /**
     * Set the link used to get the next page of IoT Central description objects.
     *
     * @param nextLink the nextLink value to set
     * @return the AppTemplatesInner object itself.
     */
    public AppTemplatesInner withNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }

    /**
     * Get a list of IoT Central Application Templates.
     *
     * @return the value value
     */
    public List<AppTemplate> value() {
        return this.value;
    }

}
