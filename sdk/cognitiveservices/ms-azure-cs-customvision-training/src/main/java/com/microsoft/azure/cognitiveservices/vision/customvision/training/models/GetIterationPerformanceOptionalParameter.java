/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.cognitiveservices.vision.customvision.training.models;


/**
 * The GetIterationPerformanceOptionalParameter model.
 */
public class GetIterationPerformanceOptionalParameter {
    /**
     * The threshold used to determine true predictions.
     */
    private Double threshold;

    /**
     * If applicable, the bounding box overlap threshold used to determine true
     * predictions.
     */
    private Double overlapThreshold;

    /**
     * Gets or sets the preferred language for the response.
     */
    private String thisclientacceptLanguage;

    /**
     * Get the threshold value.
     *
     * @return the threshold value
     */
    public Double threshold() {
        return this.threshold;
    }

    /**
     * Set the threshold value.
     *
     * @param threshold the threshold value to set
     * @return the GetIterationPerformanceOptionalParameter object itself.
     */
    public GetIterationPerformanceOptionalParameter withThreshold(Double threshold) {
        this.threshold = threshold;
        return this;
    }

    /**
     * Get the overlapThreshold value.
     *
     * @return the overlapThreshold value
     */
    public Double overlapThreshold() {
        return this.overlapThreshold;
    }

    /**
     * Set the overlapThreshold value.
     *
     * @param overlapThreshold the overlapThreshold value to set
     * @return the GetIterationPerformanceOptionalParameter object itself.
     */
    public GetIterationPerformanceOptionalParameter withOverlapThreshold(Double overlapThreshold) {
        this.overlapThreshold = overlapThreshold;
        return this;
    }

    /**
     * Get the thisclientacceptLanguage value.
     *
     * @return the thisclientacceptLanguage value
     */
    public String thisclientacceptLanguage() {
        return this.thisclientacceptLanguage;
    }

    /**
     * Set the thisclientacceptLanguage value.
     *
     * @param thisclientacceptLanguage the thisclientacceptLanguage value to set
     * @return the GetIterationPerformanceOptionalParameter object itself.
     */
    public GetIterationPerformanceOptionalParameter withThisclientacceptLanguage(String thisclientacceptLanguage) {
        this.thisclientacceptLanguage = thisclientacceptLanguage;
        return this;
    }

}
