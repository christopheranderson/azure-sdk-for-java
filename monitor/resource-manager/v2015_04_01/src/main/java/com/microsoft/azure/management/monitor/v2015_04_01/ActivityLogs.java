/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.monitor.v2015_04_01;

import rx.Observable;

/**
 * Type representing ActivityLogs.
 */
public interface ActivityLogs {
    /**
     * Provides the list of records from the activity logs.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<EventData> listAsync();

}
