/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cosmosdb.v2015_04_08.implementation;

import com.microsoft.azure.arm.resources.models.implementation.GroupableResourceCoreImpl;
import com.microsoft.azure.management.cosmosdb.v2015_04_08.DatabaseAccount;
import rx.Observable;
import com.microsoft.azure.management.cosmosdb.v2015_04_08.DatabaseAccountPatchParameters;
import java.util.List;
import com.microsoft.azure.management.cosmosdb.v2015_04_08.DatabaseAccountCreateUpdateParameters;
import com.microsoft.azure.management.cosmosdb.v2015_04_08.Capability;
import com.microsoft.azure.management.cosmosdb.v2015_04_08.ConnectorOffer;
import com.microsoft.azure.management.cosmosdb.v2015_04_08.ConsistencyPolicy;
import com.microsoft.azure.management.cosmosdb.v2015_04_08.DatabaseAccountOfferType;
import com.microsoft.azure.management.cosmosdb.v2015_04_08.FailoverPolicy;
import com.microsoft.azure.management.cosmosdb.v2015_04_08.DatabaseAccountKind;
import com.microsoft.azure.management.cosmosdb.v2015_04_08.Location;
import com.microsoft.azure.management.cosmosdb.v2015_04_08.VirtualNetworkRule;
import rx.functions.Func1;

class DatabaseAccountImpl extends GroupableResourceCoreImpl<DatabaseAccount, DatabaseAccountInner, DatabaseAccountImpl, CosmosDBManager> implements DatabaseAccount, DatabaseAccount.Definition, DatabaseAccount.Update {
    private DatabaseAccountCreateUpdateParameters createParameter;
    private DatabaseAccountPatchParameters updateParameter;
    DatabaseAccountImpl(String name, DatabaseAccountInner inner, CosmosDBManager manager) {
        super(name, inner, manager);
        this.createParameter = new DatabaseAccountCreateUpdateParameters();
        this.updateParameter = new DatabaseAccountPatchParameters();
    }

    @Override
    public Observable<DatabaseAccount> createResourceAsync() {
        DatabaseAccountsInner client = this.manager().inner().databaseAccounts();
        this.createParameter.withLocation(inner().location());
        this.createParameter.withTags(inner().getTags());
        return client.createOrUpdateAsync(this.resourceGroupName(), this.name(), this.createParameter)
            .map(new Func1<DatabaseAccountInner, DatabaseAccountInner>() {
               @Override
               public DatabaseAccountInner call(DatabaseAccountInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    public Observable<DatabaseAccount> updateResourceAsync() {
        DatabaseAccountsInner client = this.manager().inner().databaseAccounts();
        return client.patchAsync(this.resourceGroupName(), this.name(), this.updateParameter)
            .map(new Func1<DatabaseAccountInner, DatabaseAccountInner>() {
               @Override
               public DatabaseAccountInner call(DatabaseAccountInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<DatabaseAccountInner> getInnerAsync() {
        DatabaseAccountsInner client = this.manager().inner().databaseAccounts();
        return client.getByResourceGroupAsync(this.resourceGroupName(), this.name());
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }

    private void resetCreateUpdateParameters() {
        this.createParameter = new DatabaseAccountCreateUpdateParameters();
        this.updateParameter = new DatabaseAccountPatchParameters();
    }

    @Override
    public List<Capability> capabilities() {
        return this.inner().capabilities();
    }

    @Override
    public ConnectorOffer connectorOffer() {
        return this.inner().connectorOffer();
    }

    @Override
    public ConsistencyPolicy consistencyPolicy() {
        return this.inner().consistencyPolicy();
    }

    @Override
    public DatabaseAccountOfferType databaseAccountOfferType() {
        return this.inner().databaseAccountOfferType();
    }

    @Override
    public String documentEndpoint() {
        return this.inner().documentEndpoint();
    }

    @Override
    public Boolean enableAutomaticFailover() {
        return this.inner().enableAutomaticFailover();
    }

    @Override
    public Boolean enableCassandraConnector() {
        return this.inner().enableCassandraConnector();
    }

    @Override
    public Boolean enableMultipleWriteLocations() {
        return this.inner().enableMultipleWriteLocations();
    }

    @Override
    public List<FailoverPolicy> failoverPolicies() {
        return this.inner().failoverPolicies();
    }

    @Override
    public String ipRangeFilter() {
        return this.inner().ipRangeFilter();
    }

    @Override
    public Boolean isVirtualNetworkFilterEnabled() {
        return this.inner().isVirtualNetworkFilterEnabled();
    }

    @Override
    public DatabaseAccountKind kind() {
        return this.inner().kind();
    }

    @Override
    public String provisioningState() {
        return this.inner().provisioningState();
    }

    @Override
    public List<Location> readLocations() {
        return this.inner().readLocations();
    }

    @Override
    public List<VirtualNetworkRule> virtualNetworkRules() {
        return this.inner().virtualNetworkRules();
    }

    @Override
    public List<Location> writeLocations() {
        return this.inner().writeLocations();
    }

    @Override
    public DatabaseAccountImpl withDatabaseAccountOfferType(String databaseAccountOfferType) {
        this.createParameter.withDatabaseAccountOfferType(databaseAccountOfferType);
        return this;
    }

    @Override
    public DatabaseAccountImpl withLocations(List<Location> locations) {
        this.createParameter.withLocations(locations);
        return this;
    }

    @Override
    public DatabaseAccountImpl withConnectorOffer(ConnectorOffer connectorOffer) {
        this.createParameter.withConnectorOffer(connectorOffer);
        return this;
    }

    @Override
    public DatabaseAccountImpl withConsistencyPolicy(ConsistencyPolicy consistencyPolicy) {
        this.createParameter.withConsistencyPolicy(consistencyPolicy);
        return this;
    }

    @Override
    public DatabaseAccountImpl withEnableAutomaticFailover(Boolean enableAutomaticFailover) {
        this.createParameter.withEnableAutomaticFailover(enableAutomaticFailover);
        return this;
    }

    @Override
    public DatabaseAccountImpl withEnableCassandraConnector(Boolean enableCassandraConnector) {
        this.createParameter.withEnableCassandraConnector(enableCassandraConnector);
        return this;
    }

    @Override
    public DatabaseAccountImpl withEnableMultipleWriteLocations(Boolean enableMultipleWriteLocations) {
        this.createParameter.withEnableMultipleWriteLocations(enableMultipleWriteLocations);
        return this;
    }

    @Override
    public DatabaseAccountImpl withIpRangeFilter(String ipRangeFilter) {
        this.createParameter.withIpRangeFilter(ipRangeFilter);
        return this;
    }

    @Override
    public DatabaseAccountImpl withIsVirtualNetworkFilterEnabled(Boolean isVirtualNetworkFilterEnabled) {
        this.createParameter.withIsVirtualNetworkFilterEnabled(isVirtualNetworkFilterEnabled);
        return this;
    }

    @Override
    public DatabaseAccountImpl withKind(DatabaseAccountKind kind) {
        this.createParameter.withKind(kind);
        return this;
    }

    @Override
    public DatabaseAccountImpl withVirtualNetworkRules(List<VirtualNetworkRule> virtualNetworkRules) {
        this.createParameter.withVirtualNetworkRules(virtualNetworkRules);
        return this;
    }

    @Override
    public DatabaseAccountImpl withCapabilities(List<Capability> capabilities) {
        if (isInCreateMode()) {
            this.createParameter.withCapabilities(capabilities);
        } else {
            this.updateParameter.withCapabilities(capabilities);
        }
        return this;
    }

}
