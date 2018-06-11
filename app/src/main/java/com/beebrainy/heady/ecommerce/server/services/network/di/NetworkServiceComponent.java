package com.beebrainy.heady.ecommerce.server.services.network.di;

import com.beebrainy.heady.ecommerce.server.components.database.DatabaseDownloaderBO;
import com.beebrainy.heady.ecommerce.server.services.network.INetworkService;

import dagger.Component;

/**
 * Created by Prajwal Gambhir on 11-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Component(modules = NetworkServiceModule.class)
public interface NetworkServiceComponent {

    INetworkService provideNetworkService();

    void inject(DatabaseDownloaderBO dbDownloader);
}
