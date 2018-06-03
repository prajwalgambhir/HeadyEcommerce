package com.beebrainy.heady.ecommerce.server.components.repo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {

    @Provides
    @Singleton
    public IRepo provideDBService() {
        return new Repo();
    }
}
