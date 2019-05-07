/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cdn.implementation;

import com.microsoft.azure.management.cdn.PolicySettings;
import com.microsoft.azure.management.cdn.RateLimitRuleList;
import com.microsoft.azure.management.cdn.CustomRuleList;
import com.microsoft.azure.management.cdn.ManagedRuleSetList;
import java.util.List;
import com.microsoft.azure.management.cdn.CdnEndpoint;
import com.microsoft.azure.management.cdn.PolicyResourceState;
import com.microsoft.azure.management.cdn.Sku;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.azure.Resource;

/**
 * Defines web application firewall policy for Azure CDN.
 */
@JsonFlatten
public class CdnWebApplicationFirewallPolicyInner extends Resource {
    /**
     * Describes  policySettings for policy.
     */
    @JsonProperty(value = "properties.policySettings")
    private PolicySettings policySettings;

    /**
     * Describes rate limit rules inside the policy.
     */
    @JsonProperty(value = "properties.rateLimitRules")
    private RateLimitRuleList rateLimitRules;

    /**
     * Describes custom rules inside the policy.
     */
    @JsonProperty(value = "properties.customRules")
    private CustomRuleList customRules;

    /**
     * Describes managed rules inside the policy.
     */
    @JsonProperty(value = "properties.managedRules")
    private ManagedRuleSetList managedRules;

    /**
     * Describes Azure CDN endpoints associated with this Web Application
     * Firewall policy.
     */
    @JsonProperty(value = "properties.cdnEndpointLinks", access = JsonProperty.Access.WRITE_ONLY)
    private List<CdnEndpoint> cdnEndpointLinks;

    /**
     * Provisioning state of the WebApplicationFirewallPolicy.
     */
    @JsonProperty(value = "properties.provisioningState", access = JsonProperty.Access.WRITE_ONLY)
    private String provisioningState;

    /**
     * Resource status of the policy.
     * Possible values include: 'Creating', 'Enabling', 'Enabled', 'Disabling',
     * 'Disabled', 'Deleting'.
     */
    @JsonProperty(value = "properties.resourceState", access = JsonProperty.Access.WRITE_ONLY)
    private PolicyResourceState resourceState;

    /**
     * Gets a unique read-only string that changes whenever the resource is
     * updated.
     */
    @JsonProperty(value = "etag")
    private String etag;

    /**
     * The pricing tier (defines a CDN provider, feature list and rate) of the
     * CdnWebApplicationFirewallPolicy.
     */
    @JsonProperty(value = "sku", required = true)
    private Sku sku;

    /**
     * Get describes  policySettings for policy.
     *
     * @return the policySettings value
     */
    public PolicySettings policySettings() {
        return this.policySettings;
    }

    /**
     * Set describes  policySettings for policy.
     *
     * @param policySettings the policySettings value to set
     * @return the CdnWebApplicationFirewallPolicyInner object itself.
     */
    public CdnWebApplicationFirewallPolicyInner withPolicySettings(PolicySettings policySettings) {
        this.policySettings = policySettings;
        return this;
    }

    /**
     * Get describes rate limit rules inside the policy.
     *
     * @return the rateLimitRules value
     */
    public RateLimitRuleList rateLimitRules() {
        return this.rateLimitRules;
    }

    /**
     * Set describes rate limit rules inside the policy.
     *
     * @param rateLimitRules the rateLimitRules value to set
     * @return the CdnWebApplicationFirewallPolicyInner object itself.
     */
    public CdnWebApplicationFirewallPolicyInner withRateLimitRules(RateLimitRuleList rateLimitRules) {
        this.rateLimitRules = rateLimitRules;
        return this;
    }

    /**
     * Get describes custom rules inside the policy.
     *
     * @return the customRules value
     */
    public CustomRuleList customRules() {
        return this.customRules;
    }

    /**
     * Set describes custom rules inside the policy.
     *
     * @param customRules the customRules value to set
     * @return the CdnWebApplicationFirewallPolicyInner object itself.
     */
    public CdnWebApplicationFirewallPolicyInner withCustomRules(CustomRuleList customRules) {
        this.customRules = customRules;
        return this;
    }

    /**
     * Get describes managed rules inside the policy.
     *
     * @return the managedRules value
     */
    public ManagedRuleSetList managedRules() {
        return this.managedRules;
    }

    /**
     * Set describes managed rules inside the policy.
     *
     * @param managedRules the managedRules value to set
     * @return the CdnWebApplicationFirewallPolicyInner object itself.
     */
    public CdnWebApplicationFirewallPolicyInner withManagedRules(ManagedRuleSetList managedRules) {
        this.managedRules = managedRules;
        return this;
    }

    /**
     * Get describes Azure CDN endpoints associated with this Web Application Firewall policy.
     *
     * @return the cdnEndpointLinks value
     */
    public List<CdnEndpoint> cdnEndpointLinks() {
        return this.cdnEndpointLinks;
    }

    /**
     * Get provisioning state of the WebApplicationFirewallPolicy.
     *
     * @return the provisioningState value
     */
    public String provisioningState() {
        return this.provisioningState;
    }

    /**
     * Get possible values include: 'Creating', 'Enabling', 'Enabled', 'Disabling', 'Disabled', 'Deleting'.
     *
     * @return the resourceState value
     */
    public PolicyResourceState resourceState() {
        return this.resourceState;
    }

    /**
     * Get gets a unique read-only string that changes whenever the resource is updated.
     *
     * @return the etag value
     */
    public String etag() {
        return this.etag;
    }

    /**
     * Set gets a unique read-only string that changes whenever the resource is updated.
     *
     * @param etag the etag value to set
     * @return the CdnWebApplicationFirewallPolicyInner object itself.
     */
    public CdnWebApplicationFirewallPolicyInner withEtag(String etag) {
        this.etag = etag;
        return this;
    }

    /**
     * Get the pricing tier (defines a CDN provider, feature list and rate) of the CdnWebApplicationFirewallPolicy.
     *
     * @return the sku value
     */
    public Sku sku() {
        return this.sku;
    }

    /**
     * Set the pricing tier (defines a CDN provider, feature list and rate) of the CdnWebApplicationFirewallPolicy.
     *
     * @param sku the sku value to set
     * @return the CdnWebApplicationFirewallPolicyInner object itself.
     */
    public CdnWebApplicationFirewallPolicyInner withSku(Sku sku) {
        this.sku = sku;
        return this;
    }

}
