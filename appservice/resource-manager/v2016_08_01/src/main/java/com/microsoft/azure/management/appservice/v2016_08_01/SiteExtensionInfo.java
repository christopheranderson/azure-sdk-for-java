/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.appservice.v2016_08_01;

import com.microsoft.azure.arm.model.HasInner;
import com.microsoft.azure.management.appservice.v2016_08_01.implementation.SiteExtensionInfoInner;
import com.microsoft.azure.arm.model.Indexable;
import com.microsoft.azure.arm.model.Refreshable;
import com.microsoft.azure.arm.model.Updatable;
import com.microsoft.azure.arm.model.Appliable;
import com.microsoft.azure.arm.model.Creatable;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.microsoft.azure.management.appservice.v2016_08_01.implementation.AppServiceManager;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Type representing SiteExtensionInfo.
 */
public interface SiteExtensionInfo extends HasInner<SiteExtensionInfoInner>, Indexable, Refreshable<SiteExtensionInfo>, Updatable<SiteExtensionInfo.Update>, HasManager<AppServiceManager> {
    /**
     * @return the authors value.
     */
    List<String> authors();

    /**
     * @return the comment value.
     */
    String comment();

    /**
     * @return the description value.
     */
    String description();

    /**
     * @return the downloadCount value.
     */
    Integer downloadCount();

    /**
     * @return the extensionUrl value.
     */
    String extensionUrl();

    /**
     * @return the feedUrl value.
     */
    String feedUrl();

    /**
     * @return the iconUrl value.
     */
    String iconUrl();

    /**
     * @return the id value.
     */
    String id();

    /**
     * @return the installationArgs value.
     */
    String installationArgs();

    /**
     * @return the installedDateTime value.
     */
    DateTime installedDateTime();

    /**
     * @return the kind value.
     */
    String kind();

    /**
     * @return the licenseUrl value.
     */
    String licenseUrl();

    /**
     * @return the localIsLatestVersion value.
     */
    Boolean localIsLatestVersion();

    /**
     * @return the localPath value.
     */
    String localPath();

    /**
     * @return the name value.
     */
    String name();

    /**
     * @return the projectUrl value.
     */
    String projectUrl();

    /**
     * @return the provisioningState value.
     */
    String provisioningState();

    /**
     * @return the publishedDateTime value.
     */
    DateTime publishedDateTime();

    /**
     * @return the siteExtensionInfoId value.
     */
    String siteExtensionInfoId();

    /**
     * @return the siteExtensionInfoType value.
     */
    SiteExtensionType siteExtensionInfoType();

    /**
     * @return the summary value.
     */
    String summary();

    /**
     * @return the title value.
     */
    String title();

    /**
     * @return the type value.
     */
    String type();

    /**
     * @return the version value.
     */
    String version();

    /**
     * The entirety of the SiteExtensionInfo definition.
     */
    interface Definition extends DefinitionStages.Blank, DefinitionStages.WithSite, DefinitionStages.WithCreate {
    }

    /**
     * Grouping of SiteExtensionInfo definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of a SiteExtensionInfo definition.
         */
        interface Blank extends WithSite {
        }

        /**
         * The stage of the siteextensioninfo definition allowing to specify Site.
         */
        interface WithSite {
           /**
            * Specifies resourceGroupName, name.
            * @param resourceGroupName Name of the resource group to which the resource belongs
            * @param name Site name
            * @return the next definition stage
            */
            WithCreate withExistingSite(String resourceGroupName, String name);
        }

        /**
         * The stage of the definition which contains all the minimum required inputs for
         * the resource to be created (via {@link WithCreate#create()}), but also allows
         * for any other optional settings to be specified.
         */
        interface WithCreate extends Creatable<SiteExtensionInfo> {
        }
    }
    /**
     * The template for a SiteExtensionInfo update operation, containing all the settings that can be modified.
     */
    interface Update extends Appliable<SiteExtensionInfo> {
    }

    /**
     * Grouping of SiteExtensionInfo update stages.
     */
    interface UpdateStages {
    }
}
