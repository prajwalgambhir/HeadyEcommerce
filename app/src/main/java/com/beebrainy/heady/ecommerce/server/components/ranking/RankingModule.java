package com.beebrainy.heady.ecommerce.server.components.ranking;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Prajwal Gambhir on 13-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Module
public class RankingModule {

    @Provides
    IRanking getRanking() {
        return new RankingBO();
    }
}
