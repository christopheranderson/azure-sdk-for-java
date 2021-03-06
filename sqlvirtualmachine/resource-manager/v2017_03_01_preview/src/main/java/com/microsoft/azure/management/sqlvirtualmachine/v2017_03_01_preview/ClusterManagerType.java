/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.sqlvirtualmachine.v2017_03_01_preview;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.microsoft.rest.ExpandableStringEnum;

/**
 * Defines values for ClusterManagerType.
 */
public final class ClusterManagerType extends ExpandableStringEnum<ClusterManagerType> {
    /** Static value WSFC for ClusterManagerType. */
    public static final ClusterManagerType WSFC = fromString("WSFC");

    /**
     * Creates or finds a ClusterManagerType from its string representation.
     * @param name a name to look for
     * @return the corresponding ClusterManagerType
     */
    @JsonCreator
    public static ClusterManagerType fromString(String name) {
        return fromString(name, ClusterManagerType.class);
    }

    /**
     * @return known ClusterManagerType values
     */
    public static Collection<ClusterManagerType> values() {
        return values(ClusterManagerType.class);
    }
}
