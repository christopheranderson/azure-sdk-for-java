/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.iothub.v2019_03_22_preview;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.microsoft.rest.ExpandableStringEnum;

/**
 * Defines values for RouteErrorSeverity.
 */
public final class RouteErrorSeverity extends ExpandableStringEnum<RouteErrorSeverity> {
    /** Static value error for RouteErrorSeverity. */
    public static final RouteErrorSeverity ERROR = fromString("error");

    /** Static value warning for RouteErrorSeverity. */
    public static final RouteErrorSeverity WARNING = fromString("warning");

    /**
     * Creates or finds a RouteErrorSeverity from its string representation.
     * @param name a name to look for
     * @return the corresponding RouteErrorSeverity
     */
    @JsonCreator
    public static RouteErrorSeverity fromString(String name) {
        return fromString(name, RouteErrorSeverity.class);
    }

    /**
     * @return known RouteErrorSeverity values
     */
    public static Collection<RouteErrorSeverity> values() {
        return values(RouteErrorSeverity.class);
    }
}
