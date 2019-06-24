/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.apimanagement.v2018_06_01_preview;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;

/**
 * Parameters supplied to update Identity Provider.
 */
@JsonFlatten
public class IdentityProviderUpdateParameters {
    /**
     * Identity Provider Type identifier. Possible values include: 'facebook',
     * 'google', 'microsoft', 'twitter', 'aad', 'aadB2C'.
     */
    @JsonProperty(value = "properties.type")
    private IdentityProviderType type;

    /**
     * List of Allowed Tenants when configuring Azure Active Directory login.
     */
    @JsonProperty(value = "properties.allowedTenants")
    private List<String> allowedTenants;

    /**
     * OpenID Connect discovery endpoint hostname for AAD or AAD B2C.
     */
    @JsonProperty(value = "properties.authority")
    private String authority;

    /**
     * Signup Policy Name. Only applies to AAD B2C Identity Provider.
     */
    @JsonProperty(value = "properties.signupPolicyName")
    private String signupPolicyName;

    /**
     * Signin Policy Name. Only applies to AAD B2C Identity Provider.
     */
    @JsonProperty(value = "properties.signinPolicyName")
    private String signinPolicyName;

    /**
     * Profile Editing Policy Name. Only applies to AAD B2C Identity Provider.
     */
    @JsonProperty(value = "properties.profileEditingPolicyName")
    private String profileEditingPolicyName;

    /**
     * Password Reset Policy Name. Only applies to AAD B2C Identity Provider.
     */
    @JsonProperty(value = "properties.passwordResetPolicyName")
    private String passwordResetPolicyName;

    /**
     * Client Id of the Application in the external Identity Provider. It is
     * App ID for Facebook login, Client ID for Google login, App ID for
     * Microsoft.
     */
    @JsonProperty(value = "properties.clientId")
    private String clientId;

    /**
     * Client secret of the Application in external Identity Provider, used to
     * authenticate login request. For example, it is App Secret for Facebook
     * login, API Key for Google login, Public Key for Microsoft.
     */
    @JsonProperty(value = "properties.clientSecret")
    private String clientSecret;

    /**
     * Get identity Provider Type identifier. Possible values include: 'facebook', 'google', 'microsoft', 'twitter', 'aad', 'aadB2C'.
     *
     * @return the type value
     */
    public IdentityProviderType type() {
        return this.type;
    }

    /**
     * Set identity Provider Type identifier. Possible values include: 'facebook', 'google', 'microsoft', 'twitter', 'aad', 'aadB2C'.
     *
     * @param type the type value to set
     * @return the IdentityProviderUpdateParameters object itself.
     */
    public IdentityProviderUpdateParameters withType(IdentityProviderType type) {
        this.type = type;
        return this;
    }

    /**
     * Get list of Allowed Tenants when configuring Azure Active Directory login.
     *
     * @return the allowedTenants value
     */
    public List<String> allowedTenants() {
        return this.allowedTenants;
    }

    /**
     * Set list of Allowed Tenants when configuring Azure Active Directory login.
     *
     * @param allowedTenants the allowedTenants value to set
     * @return the IdentityProviderUpdateParameters object itself.
     */
    public IdentityProviderUpdateParameters withAllowedTenants(List<String> allowedTenants) {
        this.allowedTenants = allowedTenants;
        return this;
    }

    /**
     * Get openID Connect discovery endpoint hostname for AAD or AAD B2C.
     *
     * @return the authority value
     */
    public String authority() {
        return this.authority;
    }

    /**
     * Set openID Connect discovery endpoint hostname for AAD or AAD B2C.
     *
     * @param authority the authority value to set
     * @return the IdentityProviderUpdateParameters object itself.
     */
    public IdentityProviderUpdateParameters withAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    /**
     * Get signup Policy Name. Only applies to AAD B2C Identity Provider.
     *
     * @return the signupPolicyName value
     */
    public String signupPolicyName() {
        return this.signupPolicyName;
    }

    /**
     * Set signup Policy Name. Only applies to AAD B2C Identity Provider.
     *
     * @param signupPolicyName the signupPolicyName value to set
     * @return the IdentityProviderUpdateParameters object itself.
     */
    public IdentityProviderUpdateParameters withSignupPolicyName(String signupPolicyName) {
        this.signupPolicyName = signupPolicyName;
        return this;
    }

    /**
     * Get signin Policy Name. Only applies to AAD B2C Identity Provider.
     *
     * @return the signinPolicyName value
     */
    public String signinPolicyName() {
        return this.signinPolicyName;
    }

    /**
     * Set signin Policy Name. Only applies to AAD B2C Identity Provider.
     *
     * @param signinPolicyName the signinPolicyName value to set
     * @return the IdentityProviderUpdateParameters object itself.
     */
    public IdentityProviderUpdateParameters withSigninPolicyName(String signinPolicyName) {
        this.signinPolicyName = signinPolicyName;
        return this;
    }

    /**
     * Get profile Editing Policy Name. Only applies to AAD B2C Identity Provider.
     *
     * @return the profileEditingPolicyName value
     */
    public String profileEditingPolicyName() {
        return this.profileEditingPolicyName;
    }

    /**
     * Set profile Editing Policy Name. Only applies to AAD B2C Identity Provider.
     *
     * @param profileEditingPolicyName the profileEditingPolicyName value to set
     * @return the IdentityProviderUpdateParameters object itself.
     */
    public IdentityProviderUpdateParameters withProfileEditingPolicyName(String profileEditingPolicyName) {
        this.profileEditingPolicyName = profileEditingPolicyName;
        return this;
    }

    /**
     * Get password Reset Policy Name. Only applies to AAD B2C Identity Provider.
     *
     * @return the passwordResetPolicyName value
     */
    public String passwordResetPolicyName() {
        return this.passwordResetPolicyName;
    }

    /**
     * Set password Reset Policy Name. Only applies to AAD B2C Identity Provider.
     *
     * @param passwordResetPolicyName the passwordResetPolicyName value to set
     * @return the IdentityProviderUpdateParameters object itself.
     */
    public IdentityProviderUpdateParameters withPasswordResetPolicyName(String passwordResetPolicyName) {
        this.passwordResetPolicyName = passwordResetPolicyName;
        return this;
    }

    /**
     * Get client Id of the Application in the external Identity Provider. It is App ID for Facebook login, Client ID for Google login, App ID for Microsoft.
     *
     * @return the clientId value
     */
    public String clientId() {
        return this.clientId;
    }

    /**
     * Set client Id of the Application in the external Identity Provider. It is App ID for Facebook login, Client ID for Google login, App ID for Microsoft.
     *
     * @param clientId the clientId value to set
     * @return the IdentityProviderUpdateParameters object itself.
     */
    public IdentityProviderUpdateParameters withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     * Get client secret of the Application in external Identity Provider, used to authenticate login request. For example, it is App Secret for Facebook login, API Key for Google login, Public Key for Microsoft.
     *
     * @return the clientSecret value
     */
    public String clientSecret() {
        return this.clientSecret;
    }

    /**
     * Set client secret of the Application in external Identity Provider, used to authenticate login request. For example, it is App Secret for Facebook login, API Key for Google login, Public Key for Microsoft.
     *
     * @param clientSecret the clientSecret value to set
     * @return the IdentityProviderUpdateParameters object itself.
     */
    public IdentityProviderUpdateParameters withClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

}
