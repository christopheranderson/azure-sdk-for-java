/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.mariadb.v2018_06_01.implementation;

import com.microsoft.azure.management.mariadb.v2018_06_01.LogFile;
import com.microsoft.azure.arm.model.implementation.WrapperImpl;
import rx.Observable;
import org.joda.time.DateTime;

class LogFileImpl extends WrapperImpl<LogFileInner> implements LogFile {
    private final MariaDBManager manager;

    LogFileImpl(LogFileInner inner,  MariaDBManager manager) {
        super(inner);
        this.manager = manager;
    }

    @Override
    public MariaDBManager manager() {
        return this.manager;
    }



    @Override
    public DateTime createdTime() {
        return this.inner().createdTime();
    }

    @Override
    public String id() {
        return this.inner().id();
    }

    @Override
    public DateTime lastModifiedTime() {
        return this.inner().lastModifiedTime();
    }

    @Override
    public String logFileType() {
        return this.inner().logFileType();
    }

    @Override
    public String name() {
        return this.inner().name();
    }

    @Override
    public Long sizeInKB() {
        return this.inner().sizeInKB();
    }

    @Override
    public String type() {
        return this.inner().type();
    }

    @Override
    public String url() {
        return this.inner().url();
    }

}
