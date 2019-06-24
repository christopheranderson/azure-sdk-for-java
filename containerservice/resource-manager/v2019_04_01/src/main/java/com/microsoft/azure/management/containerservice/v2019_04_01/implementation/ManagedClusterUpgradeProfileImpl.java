/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.containerservice.v2019_04_01.implementation;

import com.microsoft.azure.management.containerservice.v2019_04_01.ManagedClusterUpgradeProfile;
import com.microsoft.azure.arm.model.implementation.WrapperImpl;
import java.util.List;
import com.microsoft.azure.management.containerservice.v2019_04_01.ManagedClusterPoolUpgradeProfile;

class ManagedClusterUpgradeProfileImpl extends WrapperImpl<ManagedClusterUpgradeProfileInner> implements ManagedClusterUpgradeProfile {
    private final ContainerServiceManager manager;
    ManagedClusterUpgradeProfileImpl(ManagedClusterUpgradeProfileInner inner, ContainerServiceManager manager) {
        super(inner);
        this.manager = manager;
    }

    @Override
    public ContainerServiceManager manager() {
        return this.manager;
    }

    @Override
    public List<ManagedClusterPoolUpgradeProfile> agentPoolProfiles() {
        return this.inner().agentPoolProfiles();
    }

    @Override
    public ManagedClusterPoolUpgradeProfile controlPlaneProfile() {
        return this.inner().controlPlaneProfile();
    }

    @Override
    public String id() {
        return this.inner().id();
    }

    @Override
    public String name() {
        return this.inner().name();
    }

    @Override
    public String type() {
        return this.inner().type();
    }

}
