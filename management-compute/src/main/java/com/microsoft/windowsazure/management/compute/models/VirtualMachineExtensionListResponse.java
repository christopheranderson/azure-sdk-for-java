/**
 * 
 * Copyright (c) Microsoft and contributors.  All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.windowsazure.management.compute.models;

import com.microsoft.windowsazure.core.OperationResponse;
import java.util.ArrayList;
import java.util.Iterator;

/**
* The List Resource Extensions operation response.
*/
public class VirtualMachineExtensionListResponse extends OperationResponse implements Iterable<VirtualMachineExtensionListResponse.ResourceExtension>
{
    private ArrayList<VirtualMachineExtensionListResponse.ResourceExtension> resourceExtensions;
    
    /**
    * The extensions that are available to add to your cloud service.
    * @return The ResourceExtensions value.
    */
    public ArrayList<VirtualMachineExtensionListResponse.ResourceExtension> getResourceExtensions()
    {
        return this.resourceExtensions;
    }
    
    /**
    * The extensions that are available to add to your cloud service.
    * @param resourceExtensionsValue The ResourceExtensions value.
    */
    public void setResourceExtensions(final ArrayList<VirtualMachineExtensionListResponse.ResourceExtension> resourceExtensionsValue)
    {
        this.resourceExtensions = resourceExtensionsValue;
    }
    
    /**
    * Initializes a new instance of the VirtualMachineExtensionListResponse
    * class.
    *
    */
    public VirtualMachineExtensionListResponse()
    {
        super();
        this.resourceExtensions = new ArrayList<VirtualMachineExtensionListResponse.ResourceExtension>();
    }
    
    /**
    * Gets the sequence of ResourceExtensions.
    *
    */
    public Iterator<VirtualMachineExtensionListResponse.ResourceExtension> iterator()
    {
        return this.getResourceExtensions().iterator();
    }
    
    /**
    * An extension available to add to your virtual machine.
    */
    public static class ResourceExtension
    {
        private String description;
        
        /**
        * The description of the extension.
        * @return The Description value.
        */
        public String getDescription()
        {
            return this.description;
        }
        
        /**
        * The description of the extension.
        * @param descriptionValue The Description value.
        */
        public void setDescription(final String descriptionValue)
        {
            this.description = descriptionValue;
        }
        
        private String label;
        
        /**
        * The label that is used to identify the extension.
        * @return The Label value.
        */
        public String getLabel()
        {
            return this.label;
        }
        
        /**
        * The label that is used to identify the extension.
        * @param labelValue The Label value.
        */
        public void setLabel(final String labelValue)
        {
            this.label = labelValue;
        }
        
        private String name;
        
        /**
        * The name of the extension.
        * @return The Name value.
        */
        public String getName()
        {
            return this.name;
        }
        
        /**
        * The name of the extension.
        * @param nameValue The Name value.
        */
        public void setName(final String nameValue)
        {
            this.name = nameValue;
        }
        
        private String privateConfigurationSchema;
        
        /**
        * The base64-encoded schema of the private configuration.
        * @return The PrivateConfigurationSchema value.
        */
        public String getPrivateConfigurationSchema()
        {
            return this.privateConfigurationSchema;
        }
        
        /**
        * The base64-encoded schema of the private configuration.
        * @param privateConfigurationSchemaValue The PrivateConfigurationSchema
        * value.
        */
        public void setPrivateConfigurationSchema(final String privateConfigurationSchemaValue)
        {
            this.privateConfigurationSchema = privateConfigurationSchemaValue;
        }
        
        private String publicConfigurationSchema;
        
        /**
        * The base64-encoded schema of the public configuration.
        * @return The PublicConfigurationSchema value.
        */
        public String getPublicConfigurationSchema()
        {
            return this.publicConfigurationSchema;
        }
        
        /**
        * The base64-encoded schema of the public configuration.
        * @param publicConfigurationSchemaValue The PublicConfigurationSchema
        * value.
        */
        public void setPublicConfigurationSchema(final String publicConfigurationSchemaValue)
        {
            this.publicConfigurationSchema = publicConfigurationSchemaValue;
        }
        
        private String publisher;
        
        /**
        * The provider namespace of the extension. The provider namespace for
        * Windows Azure extensions is Microsoft.Compute.
        * @return The Publisher value.
        */
        public String getPublisher()
        {
            return this.publisher;
        }
        
        /**
        * The provider namespace of the extension. The provider namespace for
        * Windows Azure extensions is Microsoft.Compute.
        * @param publisherValue The Publisher value.
        */
        public void setPublisher(final String publisherValue)
        {
            this.publisher = publisherValue;
        }
        
        private String sampleConfig;
        
        /**
        * A sample configuration file for the resource extension.
        * @return The SampleConfig value.
        */
        public String getSampleConfig()
        {
            return this.sampleConfig;
        }
        
        /**
        * A sample configuration file for the resource extension.
        * @param sampleConfigValue The SampleConfig value.
        */
        public void setSampleConfig(final String sampleConfigValue)
        {
            this.sampleConfig = sampleConfigValue;
        }
        
        private String version;
        
        /**
        * The version of the extension.
        * @return The Version value.
        */
        public String getVersion()
        {
            return this.version;
        }
        
        /**
        * The version of the extension.
        * @param versionValue The Version value.
        */
        public void setVersion(final String versionValue)
        {
            this.version = versionValue;
        }
    }
}
