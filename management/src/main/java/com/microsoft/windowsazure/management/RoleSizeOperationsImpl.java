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

package com.microsoft.windowsazure.management;

import com.microsoft.windowsazure.core.ServiceOperations;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.management.models.RoleSizeListResponse;
import com.microsoft.windowsazure.tracing.CloudTracing;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
* The Service Management API includes operations for listing the available role
* sizes for VMs in your subscription.
*/
public class RoleSizeOperationsImpl implements ServiceOperations<ManagementClientImpl>, RoleSizeOperations
{
    /**
    * Initializes a new instance of the RoleSizeOperationsImpl class.
    *
    * @param client Reference to the service client.
    */
    RoleSizeOperationsImpl(ManagementClientImpl client)
    {
        this.client = client;
    }
    
    private ManagementClientImpl client;
    
    /**
    * Gets a reference to the
    * microsoft.windowsazure.management.ManagementClientImpl.
    * @return The Client value.
    */
    public ManagementClientImpl getClient()
    {
        return this.client;
    }
    
    /**
    * The List Role Sizes operation lists all of the role sizes that are valid
    * for your subscription.
    *
    * @return The List Role Sizes operation response.
    */
    @Override
    public Future<RoleSizeListResponse> listAsync()
    {
        return this.getClient().getExecutorService().submit(new Callable<RoleSizeListResponse>() { 
            @Override
            public RoleSizeListResponse call() throws Exception
            {
                return list();
            }
         });
    }
    
    /**
    * The List Role Sizes operation lists all of the role sizes that are valid
    * for your subscription.
    *
    * @throws IOException Signals that an I/O exception of some sort has
    * occurred. This class is the general class of exceptions produced by
    * failed or interrupted I/O operations.
    * @throws ServiceException Thrown if an unexpected response is found.
    * @throws ParserConfigurationException Thrown if there was a serious
    * configuration error with the document parser.
    * @throws SAXException Thrown if there was an error parsing the XML
    * response.
    * @throws ParseException Thrown if there was an error parsing a string in
    * the response.
    * @return The List Role Sizes operation response.
    */
    @Override
    public RoleSizeListResponse list() throws IOException, ServiceException, ParserConfigurationException, SAXException, ParseException
    {
        // Validate
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace)
        {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            CloudTracing.enter(invocationId, this, "listAsync", tracingParameters);
        }
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/rolesizes";
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2013-03-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        try
        {
            if (shouldTrace)
            {
                CloudTracing.sendRequest(invocationId, httpRequest);
            }
            httpResponse = this.getClient().getHttpClient().execute(httpRequest);
            if (shouldTrace)
            {
                CloudTracing.receiveResponse(invocationId, httpResponse);
            }
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK)
            {
                ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
                if (shouldTrace)
                {
                    CloudTracing.error(invocationId, ex);
                }
                throw ex;
            }
            
            // Create Result
            RoleSizeListResponse result = null;
            // Deserialize Response
            InputStream responseContent = httpResponse.getEntity().getContent();
            result = new RoleSizeListResponse();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document responseDoc = documentBuilder.parse(responseContent);
            
            NodeList elements = responseDoc.getElementsByTagName("RoleSizes");
            Element roleSizesSequenceElement = elements.getLength() > 0 ? ((Element) elements.item(0)) : null;
            if (roleSizesSequenceElement != null)
            {
                for (int i1 = 0; i1 < roleSizesSequenceElement.getElementsByTagName("RoleSize").getLength(); i1 = i1 + 1)
                {
                    org.w3c.dom.Element roleSizesElement = ((org.w3c.dom.Element) roleSizesSequenceElement.getElementsByTagName("RoleSize").item(i1));
                    RoleSizeListResponse.RoleSize roleSizeInstance = new RoleSizeListResponse.RoleSize();
                    result.getRoleSizes().add(roleSizeInstance);
                    
                    NodeList elements2 = roleSizesElement.getElementsByTagName("Name");
                    Element nameElement = elements2.getLength() > 0 ? ((Element) elements2.item(0)) : null;
                    if (nameElement != null)
                    {
                        String nameInstance;
                        nameInstance = nameElement.getTextContent();
                        roleSizeInstance.setName(nameInstance);
                    }
                    
                    NodeList elements3 = roleSizesElement.getElementsByTagName("Label");
                    Element labelElement = elements3.getLength() > 0 ? ((Element) elements3.item(0)) : null;
                    if (labelElement != null)
                    {
                        String labelInstance;
                        labelInstance = labelElement.getTextContent();
                        roleSizeInstance.setLabel(labelInstance);
                    }
                    
                    NodeList elements4 = roleSizesElement.getElementsByTagName("Cores");
                    Element coresElement = elements4.getLength() > 0 ? ((Element) elements4.item(0)) : null;
                    if (coresElement != null)
                    {
                        int coresInstance;
                        coresInstance = Integer.parseInt(coresElement.getTextContent());
                        roleSizeInstance.setCores(coresInstance);
                    }
                    
                    NodeList elements5 = roleSizesElement.getElementsByTagName("MemoryInMb");
                    Element memoryInMbElement = elements5.getLength() > 0 ? ((Element) elements5.item(0)) : null;
                    if (memoryInMbElement != null)
                    {
                        int memoryInMbInstance;
                        memoryInMbInstance = Integer.parseInt(memoryInMbElement.getTextContent());
                        roleSizeInstance.setMemoryInMb(memoryInMbInstance);
                    }
                    
                    NodeList elements6 = roleSizesElement.getElementsByTagName("SupportedByWebWorkerRoles");
                    Element supportedByWebWorkerRolesElement = elements6.getLength() > 0 ? ((Element) elements6.item(0)) : null;
                    if (supportedByWebWorkerRolesElement != null)
                    {
                        boolean supportedByWebWorkerRolesInstance;
                        supportedByWebWorkerRolesInstance = Boolean.parseBoolean(supportedByWebWorkerRolesElement.getTextContent());
                        roleSizeInstance.setSupportedByWebWorkerRoles(supportedByWebWorkerRolesInstance);
                    }
                    
                    NodeList elements7 = roleSizesElement.getElementsByTagName("SupportedByVirtualMachines");
                    Element supportedByVirtualMachinesElement = elements7.getLength() > 0 ? ((Element) elements7.item(0)) : null;
                    if (supportedByVirtualMachinesElement != null)
                    {
                        boolean supportedByVirtualMachinesInstance;
                        supportedByVirtualMachinesInstance = Boolean.parseBoolean(supportedByVirtualMachinesElement.getTextContent());
                        roleSizeInstance.setSupportedByVirtualMachines(supportedByVirtualMachinesInstance);
                    }
                }
            }
            
            result.setStatusCode(statusCode);
            if (httpResponse.getHeaders("x-ms-request-id").length > 0)
            {
                result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
            }
            
            if (shouldTrace)
            {
                CloudTracing.exit(invocationId, result);
            }
            return result;
        }
        finally
        {
            if (httpResponse != null && httpResponse.getEntity() != null)
            {
                httpResponse.getEntity().getContent().close();
            }
        }
    }
}
