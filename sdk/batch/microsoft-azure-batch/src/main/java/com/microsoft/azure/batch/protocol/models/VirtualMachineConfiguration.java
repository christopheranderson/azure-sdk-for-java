/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.batch.protocol.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The configuration for Compute Nodes in a Pool based on the Azure Virtual
 * Machines infrastructure.
 */
public class VirtualMachineConfiguration {
    /**
     * A reference to the Azure Virtual Machines Marketplace Image or the
     * custom Virtual Machine Image to use.
     */
    @JsonProperty(value = "imageReference", required = true)
    private ImageReference imageReference;

    /**
     * The SKU of the Batch Compute Node agent to be provisioned on Compute
     * Nodes in the Pool.
     * The Batch Compute Node agent is a program that runs on each Compute Node
     * in the Pool, and provides the command-and-control interface between the
     * Compute Node and the Batch service. There are different implementations
     * of the Compute Node agent, known as SKUs, for different operating
     * systems. You must specify a Compute Node agent SKU which matches the
     * selected Image reference. To get the list of supported Compute Node
     * agent SKUs along with their list of verified Image references, see the
     * 'List supported Compute Node agent SKUs' operation.
     */
    @JsonProperty(value = "nodeAgentSKUId", required = true)
    private String nodeAgentSKUId;

    /**
     * Windows operating system settings on the virtual machine.
     * This property must not be specified if the imageReference property
     * specifies a Linux OS Image.
     */
    @JsonProperty(value = "windowsConfiguration")
    private WindowsConfiguration windowsConfiguration;

    /**
     * The configuration for data disks attached to the Compute Nodes in the
     * Pool.
     * This property must be specified if the Compute Nodes in the Pool need to
     * have empty data disks attached to them. This cannot be updated. Each
     * Compute Node gets its own disk (the disk is not a file share). Existing
     * disks cannot be attached, each attached disk is empty. When the Compute
     * Node is removed from the Pool, the disk and all data associated with it
     * is also deleted. The disk is not formatted after being attached, it must
     * be formatted before use - for more information see
     * https://docs.microsoft.com/en-us/azure/virtual-machines/linux/classic/attach-disk#initialize-a-new-data-disk-in-linux
     * and
     * https://docs.microsoft.com/en-us/azure/virtual-machines/windows/attach-disk-ps#add-an-empty-data-disk-to-a-virtual-machine.
     */
    @JsonProperty(value = "dataDisks")
    private List<DataDisk> dataDisks;

    /**
     * The type of on-premises license to be used when deploying the operating
     * system.
     * This only applies to Images that contain the Windows operating system,
     * and should only be used when you hold valid on-premises licenses for the
     * Compute Nodes which will be deployed. If omitted, no on-premises
     * licensing discount is applied. Values are:
     *
     * Windows_Server - The on-premises license is for Windows Server.
     * Windows_Client - The on-premises license is for Windows Client.
     */
    @JsonProperty(value = "licenseType")
    private String licenseType;

    /**
     * The container configuration for the Pool.
     * If specified, setup is performed on each Compute Node in the Pool to
     * allow Tasks to run in containers. All regular Tasks and Job manager
     * Tasks run on this Pool must specify the containerSettings property, and
     * all other Tasks may specify it.
     */
    @JsonProperty(value = "containerConfiguration")
    private ContainerConfiguration containerConfiguration;

    /**
     * Get the imageReference value.
     *
     * @return the imageReference value
     */
    public ImageReference imageReference() {
        return this.imageReference;
    }

    /**
     * Set the imageReference value.
     *
     * @param imageReference the imageReference value to set
     * @return the VirtualMachineConfiguration object itself.
     */
    public VirtualMachineConfiguration withImageReference(ImageReference imageReference) {
        this.imageReference = imageReference;
        return this;
    }

    /**
     * Get the Batch Compute Node agent is a program that runs on each Compute Node in the Pool, and provides the command-and-control interface between the Compute Node and the Batch service. There are different implementations of the Compute Node agent, known as SKUs, for different operating systems. You must specify a Compute Node agent SKU which matches the selected Image reference. To get the list of supported Compute Node agent SKUs along with their list of verified Image references, see the 'List supported Compute Node agent SKUs' operation.
     *
     * @return the nodeAgentSKUId value
     */
    public String nodeAgentSKUId() {
        return this.nodeAgentSKUId;
    }

    /**
     * Set the Batch Compute Node agent is a program that runs on each Compute Node in the Pool, and provides the command-and-control interface between the Compute Node and the Batch service. There are different implementations of the Compute Node agent, known as SKUs, for different operating systems. You must specify a Compute Node agent SKU which matches the selected Image reference. To get the list of supported Compute Node agent SKUs along with their list of verified Image references, see the 'List supported Compute Node agent SKUs' operation.
     *
     * @param nodeAgentSKUId the nodeAgentSKUId value to set
     * @return the VirtualMachineConfiguration object itself.
     */
    public VirtualMachineConfiguration withNodeAgentSKUId(String nodeAgentSKUId) {
        this.nodeAgentSKUId = nodeAgentSKUId;
        return this;
    }

    /**
     * Get this property must not be specified if the imageReference property specifies a Linux OS Image.
     *
     * @return the windowsConfiguration value
     */
    public WindowsConfiguration windowsConfiguration() {
        return this.windowsConfiguration;
    }

    /**
     * Set this property must not be specified if the imageReference property specifies a Linux OS Image.
     *
     * @param windowsConfiguration the windowsConfiguration value to set
     * @return the VirtualMachineConfiguration object itself.
     */
    public VirtualMachineConfiguration withWindowsConfiguration(WindowsConfiguration windowsConfiguration) {
        this.windowsConfiguration = windowsConfiguration;
        return this;
    }

    /**
     * Get this property must be specified if the Compute Nodes in the Pool need to have empty data disks attached to them. This cannot be updated. Each Compute Node gets its own disk (the disk is not a file share). Existing disks cannot be attached, each attached disk is empty. When the Compute Node is removed from the Pool, the disk and all data associated with it is also deleted. The disk is not formatted after being attached, it must be formatted before use - for more information see https://docs.microsoft.com/en-us/azure/virtual-machines/linux/classic/attach-disk#initialize-a-new-data-disk-in-linux and https://docs.microsoft.com/en-us/azure/virtual-machines/windows/attach-disk-ps#add-an-empty-data-disk-to-a-virtual-machine.
     *
     * @return the dataDisks value
     */
    public List<DataDisk> dataDisks() {
        return this.dataDisks;
    }

    /**
     * Set this property must be specified if the Compute Nodes in the Pool need to have empty data disks attached to them. This cannot be updated. Each Compute Node gets its own disk (the disk is not a file share). Existing disks cannot be attached, each attached disk is empty. When the Compute Node is removed from the Pool, the disk and all data associated with it is also deleted. The disk is not formatted after being attached, it must be formatted before use - for more information see https://docs.microsoft.com/en-us/azure/virtual-machines/linux/classic/attach-disk#initialize-a-new-data-disk-in-linux and https://docs.microsoft.com/en-us/azure/virtual-machines/windows/attach-disk-ps#add-an-empty-data-disk-to-a-virtual-machine.
     *
     * @param dataDisks the dataDisks value to set
     * @return the VirtualMachineConfiguration object itself.
     */
    public VirtualMachineConfiguration withDataDisks(List<DataDisk> dataDisks) {
        this.dataDisks = dataDisks;
        return this;
    }

    /**
     * Get this only applies to Images that contain the Windows operating system, and should only be used when you hold valid on-premises licenses for the Compute Nodes which will be deployed. If omitted, no on-premises licensing discount is applied. Values are:
      Windows_Server - The on-premises license is for Windows Server.
      Windows_Client - The on-premises license is for Windows Client.
     *
     * @return the licenseType value
     */
    public String licenseType() {
        return this.licenseType;
    }

    /**
     * Set this only applies to Images that contain the Windows operating system, and should only be used when you hold valid on-premises licenses for the Compute Nodes which will be deployed. If omitted, no on-premises licensing discount is applied. Values are:
      Windows_Server - The on-premises license is for Windows Server.
      Windows_Client - The on-premises license is for Windows Client.
     *
     * @param licenseType the licenseType value to set
     * @return the VirtualMachineConfiguration object itself.
     */
    public VirtualMachineConfiguration withLicenseType(String licenseType) {
        this.licenseType = licenseType;
        return this;
    }

    /**
     * Get if specified, setup is performed on each Compute Node in the Pool to allow Tasks to run in containers. All regular Tasks and Job manager Tasks run on this Pool must specify the containerSettings property, and all other Tasks may specify it.
     *
     * @return the containerConfiguration value
     */
    public ContainerConfiguration containerConfiguration() {
        return this.containerConfiguration;
    }

    /**
     * Set if specified, setup is performed on each Compute Node in the Pool to allow Tasks to run in containers. All regular Tasks and Job manager Tasks run on this Pool must specify the containerSettings property, and all other Tasks may specify it.
     *
     * @param containerConfiguration the containerConfiguration value to set
     * @return the VirtualMachineConfiguration object itself.
     */
    public VirtualMachineConfiguration withContainerConfiguration(ContainerConfiguration containerConfiguration) {
        this.containerConfiguration = containerConfiguration;
        return this;
    }

}
