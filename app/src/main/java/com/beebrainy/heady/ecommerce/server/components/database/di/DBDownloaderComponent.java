package com.beebrainy.heady.ecommerce.server.components.database.di;

import com.beebrainy.heady.ecommerce.server.components.category.di.CategoryModule;
import com.beebrainy.heady.ecommerce.server.components.database.DatabaseDownloaderBO;
import com.beebrainy.heady.ecommerce.server.components.ranking.RankingModule;
import com.beebrainy.heady.ecommerce.server.components.repo.di.RepoModule;
import com.beebrainy.heady.ecommerce.server.services.network.di.NetworkServiceModule;

import dagger.Component;

/**
 * Created by Prajwal Gambhir on 11-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Component(modules = {NetworkServiceModule.class, CategoryModule.class, RankingModule.class,
        RepoModule.class})
public interface DBDownloaderComponent {

    void inject(DatabaseDownloaderBO iDatabaseDownloader);
}
