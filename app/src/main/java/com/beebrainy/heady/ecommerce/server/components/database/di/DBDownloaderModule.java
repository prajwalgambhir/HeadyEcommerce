package com.beebrainy.heady.ecommerce.server.components.database.di;

import com.beebrainy.heady.ecommerce.server.components.database.DatabaseDownloaderBO;
import com.beebrainy.heady.ecommerce.server.components.database.IDatabaseDownloader;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Prajwal Gambhir on 11-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Module
public class DBDownloaderModule {

    @Provides
    IDatabaseDownloader getDatabaseDownloader() {
        return new DatabaseDownloaderBO();
    }
}
