/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * abc
 */

package com.microsoft.azure.management.mysql.v2017_12_01.implementation;

import com.microsoft.azure.arm.model.implementation.WrapperImpl;
import com.microsoft.azure.management.mysql.v2017_12_01.Locations;
import com.microsoft.azure.management.mysql.v2017_12_01.PerformanceTiers;

class LocationsImpl extends WrapperImpl<LocationBasedPerformanceTiersInner> implements Locations {
    private final MySQLManager manager;

    LocationsImpl(MySQLManager manager) {
        super(manager.inner().locationBasedPerformanceTiers());
        this.manager = manager;
    }

    @Override
    public PerformanceTiers performanceTiers() {
        PerformanceTiers accessor = this.manager().performanceTiers();
        return accessor;
    }

    public MySQLManager manager() {
        return this.manager;
    }

}
