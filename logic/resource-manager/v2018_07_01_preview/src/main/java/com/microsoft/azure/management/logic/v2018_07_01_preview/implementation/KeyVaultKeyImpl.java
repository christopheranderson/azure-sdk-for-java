/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.logic.v2018_07_01_preview.implementation;

import com.microsoft.azure.management.logic.v2018_07_01_preview.KeyVaultKey;
import com.microsoft.azure.arm.model.implementation.WrapperImpl;
import com.microsoft.azure.management.logic.v2018_07_01_preview.KeyVaultKeyAttributes;

class KeyVaultKeyImpl extends WrapperImpl<KeyVaultKeyInner> implements KeyVaultKey {
    private final LogicManager manager;
    KeyVaultKeyImpl(KeyVaultKeyInner inner, LogicManager manager) {
        super(inner);
        this.manager = manager;
    }

    @Override
    public LogicManager manager() {
        return this.manager;
    }

    @Override
    public KeyVaultKeyAttributes attributes() {
        return this.inner().attributes();
    }

    @Override
    public String kid() {
        return this.inner().kid();
    }

}
