/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.iothub.v2019_03_22_preview;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The JSON-serialized X509 Certificate.
 */
public class CertificateBodyDescription {
    /**
     * base-64 representation of the X509 leaf certificate .cer file or just
     * .pem file content.
     */
    @JsonProperty(value = "certificate")
    private String certificate;

    /**
     * Get base-64 representation of the X509 leaf certificate .cer file or just .pem file content.
     *
     * @return the certificate value
     */
    public String certificate() {
        return this.certificate;
    }

    /**
     * Set base-64 representation of the X509 leaf certificate .cer file or just .pem file content.
     *
     * @param certificate the certificate value to set
     * @return the CertificateBodyDescription object itself.
     */
    public CertificateBodyDescription withCertificate(String certificate) {
        this.certificate = certificate;
        return this;
    }

}
