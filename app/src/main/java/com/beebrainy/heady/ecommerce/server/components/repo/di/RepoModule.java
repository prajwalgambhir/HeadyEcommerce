package com.beebrainy.heady.ecommerce.server.components.repo.di;

import com.beebrainy.heady.ecommerce.server.components.repo.IRepo;
import com.beebrainy.heady.ecommerce.server.components.repo.Repo;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoModule {

    @Provides
    public IRepo provideDBService() {
        return new Repo();
    }
}
