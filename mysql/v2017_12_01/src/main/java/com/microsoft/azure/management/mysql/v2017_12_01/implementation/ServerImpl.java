/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.mysql.v2017_12_01.implementation;

import com.microsoft.azure.arm.resources.models.implementation.GroupableResourceCoreImpl;
import com.microsoft.azure.management.mysql.v2017_12_01.Server;
import com.microsoft.azure.management.mysql.v2017_12_01.ServerPropertiesForCreate;
import rx.Observable;
import com.microsoft.azure.management.mysql.v2017_12_01.ServerUpdateParameters;
import com.microsoft.azure.management.mysql.v2017_12_01.ServerForCreate;
import org.joda.time.DateTime;
import com.microsoft.azure.management.mysql.v2017_12_01.Sku;
import com.microsoft.azure.management.mysql.v2017_12_01.SslEnforcementEnum;
import com.microsoft.azure.management.mysql.v2017_12_01.StorageProfile;
import com.microsoft.azure.management.mysql.v2017_12_01.ServerState;
import com.microsoft.azure.management.mysql.v2017_12_01.ServerVersion;
import rx.functions.Func1;

class ServerImpl extends GroupableResourceCoreImpl<Server, ServerInner, ServerImpl, MySQLManager> implements Server, Server.Definition, Server.Update {
    private ServerForCreate createParameter;
    private ServerUpdateParameters updateParameter;
    ServerImpl(String name, ServerInner inner, MySQLManager manager) {
        super(name, inner, manager);
        this.createParameter = new ServerForCreate();
        this.updateParameter = new ServerUpdateParameters();
    }

    @Override
    public Observable<Server> createResourceAsync() {
        ServersInner client = this.manager().inner().servers();
        this.createParameter.withLocation(inner().location());
        this.createParameter.withTags(inner().getTags());
        return client.createAsync(this.resourceGroupName(), this.name(), this.createParameter)
            .map(new Func1<ServerInner, ServerInner>() {
               @Override
               public ServerInner call(ServerInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    public Observable<Server> updateResourceAsync() {
        ServersInner client = this.manager().inner().servers();
        return client.updateAsync(this.resourceGroupName(), this.name(), this.updateParameter)
            .map(new Func1<ServerInner, ServerInner>() {
               @Override
               public ServerInner call(ServerInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<ServerInner> getInnerAsync() {
        ServersInner client = this.manager().inner().servers();
        return client.getByResourceGroupAsync(this.resourceGroupName(), this.name());
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }

    private void resetCreateUpdateParameters() {
        this.createParameter = new ServerForCreate();
        this.updateParameter = new ServerUpdateParameters();
    }

    @Override
    public String administratorLogin() {
        return this.inner().administratorLogin();
    }

    @Override
    public DateTime earliestRestoreDate() {
        return this.inner().earliestRestoreDate();
    }

    @Override
    public String fullyQualifiedDomainName() {
        return this.inner().fullyQualifiedDomainName();
    }

    @Override
    public Sku sku() {
        return this.inner().sku();
    }

    @Override
    public SslEnforcementEnum sslEnforcement() {
        return this.inner().sslEnforcement();
    }

    @Override
    public StorageProfile storageProfile() {
        return this.inner().storageProfile();
    }

    @Override
    public ServerState userVisibleState() {
        return this.inner().userVisibleState();
    }

    @Override
    public ServerVersion version() {
        return this.inner().version();
    }

    @Override
    public ServerImpl withProperties(ServerPropertiesForCreate properties) {
        this.createParameter.withProperties(properties);
        return this;
    }

    @Override
    public ServerImpl withAdministratorLoginPassword(String administratorLoginPassword) {
        this.updateParameter.withAdministratorLoginPassword(administratorLoginPassword);
        return this;
    }

    @Override
    public ServerImpl withSslEnforcement(SslEnforcementEnum sslEnforcement) {
        this.updateParameter.withSslEnforcement(sslEnforcement);
        return this;
    }

    @Override
    public ServerImpl withStorageProfile(StorageProfile storageProfile) {
        this.updateParameter.withStorageProfile(storageProfile);
        return this;
    }

    @Override
    public ServerImpl withVersion(ServerVersion version) {
        this.updateParameter.withVersion(version);
        return this;
    }

    @Override
    public ServerImpl withSku(Sku sku) {
        if (isInCreateMode()) {
            this.createParameter.withSku(sku);
        } else {
            this.updateParameter.withSku(sku);
        }
        return this;
    }

}
