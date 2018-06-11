package com.beebrainy.heady.ecommerce.server.services.network.di;

import com.beebrainy.heady.ecommerce.server.services.network.INetworkService;
import com.beebrainy.heady.ecommerce.server.services.network.NetworkServiceBO;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Prajwal Gambhir on 11-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Module
public class NetworkServiceModule {

    @Provides
    INetworkService getNetworkService() {
        return new NetworkServiceBO();
    }

}
