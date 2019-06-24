/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.storage.v2019_04_01;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Management policy action for base blob.
 */
public class ManagementPolicyBaseBlob {
    /**
     * The function to tier blobs to cool storage. Support blobs currently at
     * Hot tier.
     */
    @JsonProperty(value = "tierToCool")
    private DateAfterModification tierToCool;

    /**
     * The function to tier blobs to archive storage. Support blobs currently
     * at Hot or Cool tier.
     */
    @JsonProperty(value = "tierToArchive")
    private DateAfterModification tierToArchive;

    /**
     * The function to delete the blob.
     */
    @JsonProperty(value = "delete")
    private DateAfterModification delete;

    /**
     * Get the function to tier blobs to cool storage. Support blobs currently at Hot tier.
     *
     * @return the tierToCool value
     */
    public DateAfterModification tierToCool() {
        return this.tierToCool;
    }

    /**
     * Set the function to tier blobs to cool storage. Support blobs currently at Hot tier.
     *
     * @param tierToCool the tierToCool value to set
     * @return the ManagementPolicyBaseBlob object itself.
     */
    public ManagementPolicyBaseBlob withTierToCool(DateAfterModification tierToCool) {
        this.tierToCool = tierToCool;
        return this;
    }

    /**
     * Get the function to tier blobs to archive storage. Support blobs currently at Hot or Cool tier.
     *
     * @return the tierToArchive value
     */
    public DateAfterModification tierToArchive() {
        return this.tierToArchive;
    }

    /**
     * Set the function to tier blobs to archive storage. Support blobs currently at Hot or Cool tier.
     *
     * @param tierToArchive the tierToArchive value to set
     * @return the ManagementPolicyBaseBlob object itself.
     */
    public ManagementPolicyBaseBlob withTierToArchive(DateAfterModification tierToArchive) {
        this.tierToArchive = tierToArchive;
        return this;
    }

    /**
     * Get the function to delete the blob.
     *
     * @return the delete value
     */
    public DateAfterModification delete() {
        return this.delete;
    }

    /**
     * Set the function to delete the blob.
     *
     * @param delete the delete value to set
     * @return the ManagementPolicyBaseBlob object itself.
     */
    public ManagementPolicyBaseBlob withDelete(DateAfterModification delete) {
        this.delete = delete;
        return this;
    }

}
