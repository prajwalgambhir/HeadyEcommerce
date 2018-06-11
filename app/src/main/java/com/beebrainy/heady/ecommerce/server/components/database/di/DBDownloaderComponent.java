package com.beebrainy.heady.ecommerce.server.components.database.di;

import com.beebrainy.heady.ecommerce.client.activities.MainActivity;
import com.beebrainy.heady.ecommerce.server.components.database.IDatabaseDownloader;

import dagger.Component;

/**
 * Created by Prajwal Gambhir on 11-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Component(modules = {DBDownloaderModule.class})
public interface DBDownloaderComponent {

    IDatabaseDownloader provideDbDownloader();

    void inject(MainActivity mainActivity);
}
