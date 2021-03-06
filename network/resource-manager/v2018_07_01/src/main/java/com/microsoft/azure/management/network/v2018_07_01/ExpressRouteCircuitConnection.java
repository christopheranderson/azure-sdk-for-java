/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.network.v2018_07_01;

import com.microsoft.azure.arm.model.HasInner;
import com.microsoft.azure.management.network.v2018_07_01.implementation.ExpressRouteCircuitConnectionInner;
import com.microsoft.azure.arm.model.Indexable;
import com.microsoft.azure.arm.model.Refreshable;
import com.microsoft.azure.arm.model.Updatable;
import com.microsoft.azure.arm.model.Appliable;
import com.microsoft.azure.arm.model.Creatable;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.microsoft.azure.management.network.v2018_07_01.implementation.NetworkManager;
import com.microsoft.azure.SubResource;

/**
 * Type representing ExpressRouteCircuitConnection.
 */
public interface ExpressRouteCircuitConnection extends HasInner<ExpressRouteCircuitConnectionInner>, Indexable, Refreshable<ExpressRouteCircuitConnection>, Updatable<ExpressRouteCircuitConnection.Update>, HasManager<NetworkManager> {
    /**
     * @return the addressPrefix value.
     */
    String addressPrefix();

    /**
     * @return the authorizationKey value.
     */
    String authorizationKey();

    /**
     * @return the circuitConnectionStatus value.
     */
    CircuitConnectionStatus circuitConnectionStatus();

    /**
     * @return the etag value.
     */
    String etag();

    /**
     * @return the expressRouteCircuitPeering value.
     */
    SubResource expressRouteCircuitPeering();

    /**
     * @return the id value.
     */
    String id();

    /**
     * @return the name value.
     */
    String name();

    /**
     * @return the peerExpressRouteCircuitPeering value.
     */
    SubResource peerExpressRouteCircuitPeering();

    /**
     * @return the provisioningState value.
     */
    String provisioningState();

    /**
     * The entirety of the ExpressRouteCircuitConnection definition.
     */
    interface Definition extends DefinitionStages.Blank, DefinitionStages.WithPeering, DefinitionStages.WithCreate {
    }

    /**
     * Grouping of ExpressRouteCircuitConnection definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of a ExpressRouteCircuitConnection definition.
         */
        interface Blank extends WithPeering {
        }

        /**
         * The stage of the expressroutecircuitconnection definition allowing to specify Peering.
         */
        interface WithPeering {
           /**
            * Specifies resourceGroupName, circuitName, peeringName.
            * @param resourceGroupName The name of the resource group
            * @param circuitName The name of the express route circuit
            * @param peeringName The name of the peering
            * @return the next definition stage
            */
            WithCreate withExistingPeering(String resourceGroupName, String circuitName, String peeringName);
        }

        /**
         * The stage of the expressroutecircuitconnection definition allowing to specify AddressPrefix.
         */
        interface WithAddressPrefix {
            /**
             * Specifies addressPrefix.
             * @param addressPrefix /29 IP address space to carve out Customer addresses for tunnels
             * @return the next definition stage
             */
            WithCreate withAddressPrefix(String addressPrefix);
        }

        /**
         * The stage of the expressroutecircuitconnection definition allowing to specify AuthorizationKey.
         */
        interface WithAuthorizationKey {
            /**
             * Specifies authorizationKey.
             * @param authorizationKey The authorization key
             * @return the next definition stage
             */
            WithCreate withAuthorizationKey(String authorizationKey);
        }

        /**
         * The stage of the expressroutecircuitconnection definition allowing to specify ExpressRouteCircuitPeering.
         */
        interface WithExpressRouteCircuitPeering {
            /**
             * Specifies expressRouteCircuitPeering.
             * @param expressRouteCircuitPeering Reference to Express Route Circuit Private Peering Resource of the circuit initiating connection
             * @return the next definition stage
             */
            WithCreate withExpressRouteCircuitPeering(SubResource expressRouteCircuitPeering);
        }

        /**
         * The stage of the expressroutecircuitconnection definition allowing to specify Id.
         */
        interface WithId {
            /**
             * Specifies id.
             * @param id Resource ID
             * @return the next definition stage
             */
            WithCreate withId(String id);
        }

        /**
         * The stage of the expressroutecircuitconnection definition allowing to specify Name.
         */
        interface WithName {
            /**
             * Specifies name.
             * @param name Gets name of the resource that is unique within a resource group. This name can be used to access the resource
             * @return the next definition stage
             */
            WithCreate withName(String name);
        }

        /**
         * The stage of the expressroutecircuitconnection definition allowing to specify PeerExpressRouteCircuitPeering.
         */
        interface WithPeerExpressRouteCircuitPeering {
            /**
             * Specifies peerExpressRouteCircuitPeering.
             * @param peerExpressRouteCircuitPeering Reference to Express Route Circuit Private Peering Resource of the peered circuit
             * @return the next definition stage
             */
            WithCreate withPeerExpressRouteCircuitPeering(SubResource peerExpressRouteCircuitPeering);
        }

        /**
         * The stage of the definition which contains all the minimum required inputs for
         * the resource to be created (via {@link WithCreate#create()}), but also allows
         * for any other optional settings to be specified.
         */
        interface WithCreate extends Creatable<ExpressRouteCircuitConnection>, DefinitionStages.WithAddressPrefix, DefinitionStages.WithAuthorizationKey, DefinitionStages.WithExpressRouteCircuitPeering, DefinitionStages.WithId, DefinitionStages.WithName, DefinitionStages.WithPeerExpressRouteCircuitPeering {
        }
    }
    /**
     * The template for a ExpressRouteCircuitConnection update operation, containing all the settings that can be modified.
     */
    interface Update extends Appliable<ExpressRouteCircuitConnection>, UpdateStages.WithAddressPrefix, UpdateStages.WithAuthorizationKey, UpdateStages.WithExpressRouteCircuitPeering, UpdateStages.WithId, UpdateStages.WithName, UpdateStages.WithPeerExpressRouteCircuitPeering {
    }

    /**
     * Grouping of ExpressRouteCircuitConnection update stages.
     */
    interface UpdateStages {
        /**
         * The stage of the expressroutecircuitconnection update allowing to specify AddressPrefix.
         */
        interface WithAddressPrefix {
            /**
             * Specifies addressPrefix.
             * @param addressPrefix /29 IP address space to carve out Customer addresses for tunnels
             * @return the next update stage
             */
            Update withAddressPrefix(String addressPrefix);
        }

        /**
         * The stage of the expressroutecircuitconnection update allowing to specify AuthorizationKey.
         */
        interface WithAuthorizationKey {
            /**
             * Specifies authorizationKey.
             * @param authorizationKey The authorization key
             * @return the next update stage
             */
            Update withAuthorizationKey(String authorizationKey);
        }

        /**
         * The stage of the expressroutecircuitconnection update allowing to specify ExpressRouteCircuitPeering.
         */
        interface WithExpressRouteCircuitPeering {
            /**
             * Specifies expressRouteCircuitPeering.
             * @param expressRouteCircuitPeering Reference to Express Route Circuit Private Peering Resource of the circuit initiating connection
             * @return the next update stage
             */
            Update withExpressRouteCircuitPeering(SubResource expressRouteCircuitPeering);
        }

        /**
         * The stage of the expressroutecircuitconnection update allowing to specify Id.
         */
        interface WithId {
            /**
             * Specifies id.
             * @param id Resource ID
             * @return the next update stage
             */
            Update withId(String id);
        }

        /**
         * The stage of the expressroutecircuitconnection update allowing to specify Name.
         */
        interface WithName {
            /**
             * Specifies name.
             * @param name Gets name of the resource that is unique within a resource group. This name can be used to access the resource
             * @return the next update stage
             */
            Update withName(String name);
        }

        /**
         * The stage of the expressroutecircuitconnection update allowing to specify PeerExpressRouteCircuitPeering.
         */
        interface WithPeerExpressRouteCircuitPeering {
            /**
             * Specifies peerExpressRouteCircuitPeering.
             * @param peerExpressRouteCircuitPeering Reference to Express Route Circuit Private Peering Resource of the peered circuit
             * @return the next update stage
             */
            Update withPeerExpressRouteCircuitPeering(SubResource peerExpressRouteCircuitPeering);
        }

    }
}
