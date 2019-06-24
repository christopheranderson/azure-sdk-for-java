/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.apimanagement.v2018_06_01_preview;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Parameters supplied to the Backup/Restore of an API Management service
 * operation.
 */
public class ApiManagementServiceBackupRestoreParameters {
    /**
     * Azure Cloud Storage account (used to place/retrieve the backup) name.
     */
    @JsonProperty(value = "storageAccount", required = true)
    private String storageAccount;

    /**
     * Azure Cloud Storage account (used to place/retrieve the backup) access
     * key.
     */
    @JsonProperty(value = "accessKey", required = true)
    private String accessKey;

    /**
     * Azure Cloud Storage blob container name used to place/retrieve the
     * backup.
     */
    @JsonProperty(value = "containerName", required = true)
    private String containerName;

    /**
     * The name of the backup file to create.
     */
    @JsonProperty(value = "backupName", required = true)
    private String backupName;

    /**
     * Get azure Cloud Storage account (used to place/retrieve the backup) name.
     *
     * @return the storageAccount value
     */
    public String storageAccount() {
        return this.storageAccount;
    }

    /**
     * Set azure Cloud Storage account (used to place/retrieve the backup) name.
     *
     * @param storageAccount the storageAccount value to set
     * @return the ApiManagementServiceBackupRestoreParameters object itself.
     */
    public ApiManagementServiceBackupRestoreParameters withStorageAccount(String storageAccount) {
        this.storageAccount = storageAccount;
        return this;
    }

    /**
     * Get azure Cloud Storage account (used to place/retrieve the backup) access key.
     *
     * @return the accessKey value
     */
    public String accessKey() {
        return this.accessKey;
    }

    /**
     * Set azure Cloud Storage account (used to place/retrieve the backup) access key.
     *
     * @param accessKey the accessKey value to set
     * @return the ApiManagementServiceBackupRestoreParameters object itself.
     */
    public ApiManagementServiceBackupRestoreParameters withAccessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    /**
     * Get azure Cloud Storage blob container name used to place/retrieve the backup.
     *
     * @return the containerName value
     */
    public String containerName() {
        return this.containerName;
    }

    /**
     * Set azure Cloud Storage blob container name used to place/retrieve the backup.
     *
     * @param containerName the containerName value to set
     * @return the ApiManagementServiceBackupRestoreParameters object itself.
     */
    public ApiManagementServiceBackupRestoreParameters withContainerName(String containerName) {
        this.containerName = containerName;
        return this;
    }

    /**
     * Get the name of the backup file to create.
     *
     * @return the backupName value
     */
    public String backupName() {
        return this.backupName;
    }

    /**
     * Set the name of the backup file to create.
     *
     * @param backupName the backupName value to set
     * @return the ApiManagementServiceBackupRestoreParameters object itself.
     */
    public ApiManagementServiceBackupRestoreParameters withBackupName(String backupName) {
        this.backupName = backupName;
        return this;
    }

}
