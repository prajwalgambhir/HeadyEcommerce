package com.beebrainy.heady.ecommerce.client.activities.di;

import com.beebrainy.heady.ecommerce.client.activities.MainActivity;
import com.beebrainy.heady.ecommerce.client.service.di.StorageServiceModule;
import com.beebrainy.heady.ecommerce.server.components.database.di.DBDownloaderModule;

import dagger.Component;

/**
 * Created by Prajwal Gambhir on 13-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Component(modules = {DBDownloaderModule.class, StorageServiceModule.class})
public interface MAComponent {

    void inject(MainActivity ma);
}
