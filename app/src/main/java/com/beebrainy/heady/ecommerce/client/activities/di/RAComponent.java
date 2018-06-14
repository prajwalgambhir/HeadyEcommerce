package com.beebrainy.heady.ecommerce.client.activities.di;

import com.beebrainy.heady.ecommerce.client.activities.RankingActivity;
import com.beebrainy.heady.ecommerce.server.components.ranking.RankingModule;

import dagger.Component;

/**
 * Created by Prajwal Gambhir on 13-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Component(modules = {RankingModule.class})
public interface RAComponent {

    void inject(RankingActivity ra);
}
