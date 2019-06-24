/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.monitor.v2017_05_01_preview;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a metric metadata value.
 */
public class MetadataValue {
    /**
     * the name of the metadata.
     */
    @JsonProperty(value = "name")
    private LocalizableString name;

    /**
     * the value of the metadata.
     */
    @JsonProperty(value = "value")
    private String value;

    /**
     * Get the name of the metadata.
     *
     * @return the name value
     */
    public LocalizableString name() {
        return this.name;
    }

    /**
     * Set the name of the metadata.
     *
     * @param name the name value to set
     * @return the MetadataValue object itself.
     */
    public MetadataValue withName(LocalizableString name) {
        this.name = name;
        return this;
    }

    /**
     * Get the value of the metadata.
     *
     * @return the value value
     */
    public String value() {
        return this.value;
    }

    /**
     * Set the value of the metadata.
     *
     * @param value the value value to set
     * @return the MetadataValue object itself.
     */
    public MetadataValue withValue(String value) {
        this.value = value;
        return this;
    }

}
