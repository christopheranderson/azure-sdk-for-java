// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.storage.file.models;

import com.azure.core.implementation.DateTimeRfc1123;
import com.azure.core.implementation.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.OffsetDateTime;

/**
 * Defines headers for StartCopy operation.
 */
@JacksonXmlRootElement(localName = "File-StartCopy-Headers")
@Fluent
public final class FileStartCopyHeaders {
    /*
     * If the copy is completed, contains the ETag of the destination file. If
     * the copy is not complete, contains the ETag of the empty file created at
     * the start of the copy.
     */
    @JsonProperty(value = "ETag")
    private String eTag;

    /*
     * Returns the date/time that the copy operation to the destination file
     * completed.
     */
    @JsonProperty(value = "Last-Modified")
    private DateTimeRfc1123 lastModified;

    /*
     * This header uniquely identifies the request that was made and can be
     * used for troubleshooting the request.
     */
    @JsonProperty(value = "x-ms-request-id")
    private String requestId;

    /*
     * Indicates the version of the File service used to execute the request.
     */
    @JsonProperty(value = "x-ms-version")
    private String version;

    /*
     * A UTC date/time value generated by the service that indicates the time
     * at which the response was initiated.
     */
    @JsonProperty(value = "Date")
    private DateTimeRfc1123 dateProperty;

    /*
     * String identifier for this copy operation. Use with Get File or Get File
     * Properties to check the status of this copy operation, or pass to Abort
     * Copy File to abort a pending copy.
     */
    @JsonProperty(value = "x-ms-copy-id")
    private String copyId;

    /*
     * State of the copy operation identified by x-ms-copy-id. Possible values
     * include: 'pending', 'success', 'aborted', 'failed'
     */
    @JsonProperty(value = "x-ms-copy-status")
    private CopyStatusType copyStatus;

    /*
     * The errorCode property.
     */
    @JsonProperty(value = "x-ms-error-code")
    private String errorCode;

    /**
     * Get the eTag property: If the copy is completed, contains the ETag of
     * the destination file. If the copy is not complete, contains the ETag of
     * the empty file created at the start of the copy.
     *
     * @return the eTag value.
     */
    public String eTag() {
        return this.eTag;
    }

    /**
     * Set the eTag property: If the copy is completed, contains the ETag of
     * the destination file. If the copy is not complete, contains the ETag of
     * the empty file created at the start of the copy.
     *
     * @param eTag the eTag value to set.
     * @return the FileStartCopyHeaders object itself.
     */
    public FileStartCopyHeaders eTag(String eTag) {
        this.eTag = eTag;
        return this;
    }

    /**
     * Get the lastModified property: Returns the date/time that the copy
     * operation to the destination file completed.
     *
     * @return the lastModified value.
     */
    public OffsetDateTime lastModified() {
        if (this.lastModified == null) {
            return null;
        }
        return this.lastModified.dateTime();
    }

    /**
     * Set the lastModified property: Returns the date/time that the copy
     * operation to the destination file completed.
     *
     * @param lastModified the lastModified value to set.
     * @return the FileStartCopyHeaders object itself.
     */
    public FileStartCopyHeaders lastModified(OffsetDateTime lastModified) {
        if (lastModified == null) {
            this.lastModified = null;
        } else {
            this.lastModified = new DateTimeRfc1123(lastModified);
        }
        return this;
    }

    /**
     * Get the requestId property: This header uniquely identifies the request
     * that was made and can be used for troubleshooting the request.
     *
     * @return the requestId value.
     */
    public String requestId() {
        return this.requestId;
    }

    /**
     * Set the requestId property: This header uniquely identifies the request
     * that was made and can be used for troubleshooting the request.
     *
     * @param requestId the requestId value to set.
     * @return the FileStartCopyHeaders object itself.
     */
    public FileStartCopyHeaders requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * Get the version property: Indicates the version of the File service used
     * to execute the request.
     *
     * @return the version value.
     */
    public String version() {
        return this.version;
    }

    /**
     * Set the version property: Indicates the version of the File service used
     * to execute the request.
     *
     * @param version the version value to set.
     * @return the FileStartCopyHeaders object itself.
     */
    public FileStartCopyHeaders version(String version) {
        this.version = version;
        return this;
    }

    /**
     * Get the dateProperty property: A UTC date/time value generated by the
     * service that indicates the time at which the response was initiated.
     *
     * @return the dateProperty value.
     */
    public OffsetDateTime dateProperty() {
        if (this.dateProperty == null) {
            return null;
        }
        return this.dateProperty.dateTime();
    }

    /**
     * Set the dateProperty property: A UTC date/time value generated by the
     * service that indicates the time at which the response was initiated.
     *
     * @param dateProperty the dateProperty value to set.
     * @return the FileStartCopyHeaders object itself.
     */
    public FileStartCopyHeaders dateProperty(OffsetDateTime dateProperty) {
        if (dateProperty == null) {
            this.dateProperty = null;
        } else {
            this.dateProperty = new DateTimeRfc1123(dateProperty);
        }
        return this;
    }

    /**
     * Get the copyId property: String identifier for this copy operation. Use
     * with Get File or Get File Properties to check the status of this copy
     * operation, or pass to Abort Copy File to abort a pending copy.
     *
     * @return the copyId value.
     */
    public String copyId() {
        return this.copyId;
    }

    /**
     * Set the copyId property: String identifier for this copy operation. Use
     * with Get File or Get File Properties to check the status of this copy
     * operation, or pass to Abort Copy File to abort a pending copy.
     *
     * @param copyId the copyId value to set.
     * @return the FileStartCopyHeaders object itself.
     */
    public FileStartCopyHeaders copyId(String copyId) {
        this.copyId = copyId;
        return this;
    }

    /**
     * Get the copyStatus property: State of the copy operation identified by
     * x-ms-copy-id. Possible values include: 'pending', 'success', 'aborted',
     * 'failed'.
     *
     * @return the copyStatus value.
     */
    public CopyStatusType copyStatus() {
        return this.copyStatus;
    }

    /**
     * Set the copyStatus property: State of the copy operation identified by
     * x-ms-copy-id. Possible values include: 'pending', 'success', 'aborted',
     * 'failed'.
     *
     * @param copyStatus the copyStatus value to set.
     * @return the FileStartCopyHeaders object itself.
     */
    public FileStartCopyHeaders copyStatus(CopyStatusType copyStatus) {
        this.copyStatus = copyStatus;
        return this;
    }

    /**
     * Get the errorCode property: The errorCode property.
     *
     * @return the errorCode value.
     */
    public String errorCode() {
        return this.errorCode;
    }

    /**
     * Set the errorCode property: The errorCode property.
     *
     * @param errorCode the errorCode value to set.
     * @return the FileStartCopyHeaders object itself.
     */
    public FileStartCopyHeaders errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
