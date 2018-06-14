package com.beebrainy.heady.ecommerce.server.components.ranking.di;

import com.beebrainy.heady.ecommerce.server.components.ranking.RankingBO;
import com.beebrainy.heady.ecommerce.server.components.repo.di.RepoModule;

import dagger.Component;

/**
 * Created by Prajwal Gambhir on 13-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Component(modules = {RepoModule.class})
public interface RankingComponent {

    void inject(RankingBO ranking);
}
