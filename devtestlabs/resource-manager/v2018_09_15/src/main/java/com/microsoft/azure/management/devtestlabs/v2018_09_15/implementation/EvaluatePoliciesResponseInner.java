/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.devtestlabs.v2018_09_15.implementation;

import java.util.List;
import com.microsoft.azure.management.devtestlabs.v2018_09_15.PolicySetResult;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response body for evaluating a policy set.
 */
public class EvaluatePoliciesResponseInner {
    /**
     * Results of evaluating a policy set.
     */
    @JsonProperty(value = "results")
    private List<PolicySetResult> results;

    /**
     * Get results of evaluating a policy set.
     *
     * @return the results value
     */
    public List<PolicySetResult> results() {
        return this.results;
    }

    /**
     * Set results of evaluating a policy set.
     *
     * @param results the results value to set
     * @return the EvaluatePoliciesResponseInner object itself.
     */
    public EvaluatePoliciesResponseInner withResults(List<PolicySetResult> results) {
        this.results = results;
        return this;
    }

}
