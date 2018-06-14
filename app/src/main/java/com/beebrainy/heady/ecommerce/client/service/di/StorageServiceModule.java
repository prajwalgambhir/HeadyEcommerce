package com.beebrainy.heady.ecommerce.client.service.di;

import android.content.Context;

import com.beebrainy.heady.ecommerce.client.service.StorageService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Prajwal Gambhir on 13-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Module
public class StorageServiceModule {

    @Provides
    StorageService providesStorageService(Context context) {
        return new StorageService(context);
    }
}
