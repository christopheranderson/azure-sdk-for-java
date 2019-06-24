// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.keyvault.keys.models;

import com.azure.keyvault.keys.models.webkey.JsonWebKey;
import com.azure.keyvault.keys.models.webkey.KeyCurveName;
import com.azure.keyvault.keys.models.webkey.KeyOperation;
import com.azure.keyvault.keys.models.webkey.KeyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.binary.Base64;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeyBase {

    /**
     * Determines whether the object is enabled.
     */
    private Boolean enabled;

    /**
     * Not before date in UTC.
     */
    private OffsetDateTime notBefore;

    /**
     * The key version.
     */
    String version;

    /**
     * Expiry date in UTC.
     */
    private OffsetDateTime expires;

    /**
     * Creation time in UTC.
     */
    private OffsetDateTime created;

    /**
     * Last updated time in UTC.
     */
    private OffsetDateTime updated;

    /**
     * Reflects the deletion recovery level currently in effect for keys in
     * the current vault. If it contains 'Purgeable', the key can be
     * permanently deleted by a privileged user; otherwise, only the system can
     * purge the key, at the end of the retention interval. Possible values
     * include: 'Purgeable', 'Recoverable+Purgeable', 'Recoverable',
     * 'Recoverable+ProtectedSubscription'.
     */
    private String recoveryLevel;

    /**
     * The key name.
     */
    String name;

    /**
     * Key identifier.
     */
    @JsonProperty(value = "kid")
    String id;

    /**
     * Application specific metadata in the form of key-value pairs.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /**
     * True if the key's lifetime is managed by key vault. If this is a key
     * backing a certificate, then managed will be true.
     */
    @JsonProperty(value = "managed", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean managed;

    /**
     * Get the recoveryLevel value.
     *
     * @return the recoveryLevel value
     */
    public String recoveryLevel() {
        return this.recoveryLevel;
    }

    /**
     * Get the key name.
     *
     * @return the name of the key.
     */
    public String name() {
        return this.name;
    }


    /**
     * Get the enabled value.
     *
     * @return the enabled value
     */
    public Boolean enabled() {
        return this.enabled;
    }

    /**
     * Set the enabled value.
     *
     * @param enabled The enabled value to set
     * @return the KeyBase object itself.
     */
    public KeyBase enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Get the notBefore UTC time.
     *
     * @return the notBefore UTC time.
     */
    public OffsetDateTime notBefore() {
        return notBefore;
    }

    /**
     * Set the {@link OffsetDateTime notBefore} UTC time.
     *
     * @param notBefore The notBefore UTC time to set
     * @return the KeyBase object itself.
     */
    public KeyBase notBefore(OffsetDateTime notBefore) {
        this.notBefore = notBefore;
        return this;
    }

    /**
     * Get the Key Expiry time in UTC.
     *
     * @return the expires UTC time.
     */
    public OffsetDateTime expires() {
        if (this.expires == null) {
            return null;
        }
        return this.expires;
    }

    /**
     * Set the {@link OffsetDateTime expires} UTC time.
     *
     * @param expires The expiry time to set for the key.
     * @return the KeyBase object itself.
     */
    public KeyBase expires(OffsetDateTime expires) {
        this.expires = expires;
        return this;
    }

    /**
     * Get the the UTC time at which key was created.
     *
     * @return the created UTC time.
     */
    public OffsetDateTime created() {
        return created;
    }

    /**
     * Get the UTC time at which key was last updated.
     *
     * @return the last updated UTC time.
     */
    public OffsetDateTime updated() {
        return updated;
    }

    /**
     * Get the key identifier.
     *
     * @return the key identifier.
     */
    public String id() {
        return this.id;
    }


    /**
     * Get the tags associated with the key.
     *
     * @return the value of the tags.
     */
    public Map<String, String> tags() {
        return this.tags;
    }

    /**
     * Set the tags to be associated with the key.
     *
     * @param tags The tags to set
     * @return the KeyBase object itself.
     */
    public KeyBase tags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the managed value.
     *
     * @return the managed value
     */
    public Boolean managed() {
        return this.managed;
    }

    /**
     * Get the version of the key.
     *
     * @return the version of the key.
     */
    public String version() {
        return this.version;
    }

    /**
     * Unpacks the attributes json response and updates the variables in the Key Attributes object.
     * Uses Lazy Update to set values for variables id, tags, contentType, managed and id as these variables are
     * part of main json body and not attributes json body when the key response comes from list keys operations.
     * @param attributes The key value mapping of the key attributes
     */
    @JsonProperty("attributes")
    private void unpackAttributes(Map<String, Object> attributes) {
        this.enabled = (Boolean) attributes.get("enabled");
        this.notBefore =  epochToOffsetDateTime(attributes.get("nbf"));
        this.expires =  epochToOffsetDateTime(attributes.get("exp"));
        this.created = epochToOffsetDateTime(attributes.get("created"));
        this.updated = epochToOffsetDateTime(attributes.get("updated"));
        this.recoveryLevel = (String) attributes.get("recoveryLevel");
        this.tags = (Map<String, String>) lazyValueSelection(attributes.get("tags"), this.tags);
        this.managed = (Boolean) lazyValueSelection(attributes.get("managed"), this.managed);
        unpackId((String) lazyValueSelection(attributes.get("id"), this.id));
    }

    private OffsetDateTime epochToOffsetDateTime(Object epochValue) {
        if (epochValue != null) {
            Instant instant = Instant.ofEpochMilli(((Number) epochValue).longValue() * 1000L);
            return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
        }
        return null;
    }

    Object lazyValueSelection(Object input1, Object input2) {
        if (input1 == null) {
            return input2;
        }
        return input1;
    }

    @JsonProperty(value = "kid")
    void unpackId(String keyId) {
        if (keyId != null && keyId.length() > 0) {
            this.id = keyId;
            try {
                URL url = new URL(keyId);
                String[] tokens = url.getPath().split("/");
                this.name = (tokens.length >= 3 ? tokens[2] : null);
                this.version = (tokens.length >= 4 ? tokens[3] : null);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    List<KeyOperation> getKeyOperations(List<String> jsonWebKeyOps) {
        List<KeyOperation> output = new ArrayList<>();
        for (String keyOp : jsonWebKeyOps) {
            output.add(KeyOperation.fromString(keyOp));
        }
        return output;
    }

    JsonWebKey createKeyMaterialFromJson(Map<String, Object> key) {
        final Base64 base64 = new Base64(-1, null, true);
        JsonWebKey outputKey = new JsonWebKey()
                .y(base64.decode((String) key.get("y")))
                .x(base64.decode((String) key.get("x")))
                .crv(KeyCurveName.fromString((String) key.get("crv")))
                .keyOps(getKeyOperations((List<String>) key.get("key_ops")))
                .t(base64.decode((String) key.get("key_hsm")))
                .k(base64.decode((String) key.get("k")))
                .q(base64.decode((String) key.get("q")))
                .p(base64.decode((String) key.get("p")))
                .qi(base64.decode((String) key.get("qi")))
                .dq(base64.decode((String) key.get("dq")))
                .dp(base64.decode((String) key.get("dp")))
                .d(base64.decode((String) key.get("d")))
                .e(base64.decode((String) key.get("e")))
                .n(base64.decode((String) key.get("n")))
                .kty(KeyType.fromString((String) key.get("kty")))
                .kid((String) key.get("kid"));
        unpackId((String) key.get("kid"));
        return outputKey;
    }
}
