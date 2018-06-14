package com.beebrainy.heady.ecommerce.client.activities.di;

import com.beebrainy.heady.ecommerce.client.activities.ProductListActivity;
import com.beebrainy.heady.ecommerce.server.components.category.di.CategoryModule;
import com.beebrainy.heady.ecommerce.server.components.ranking.RankingModule;

import dagger.Component;

/**
 * Created by Prajwal Gambhir on 14-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Component(modules = {CategoryModule.class, RankingModule.class})
public interface PLAComponent {

    void inject(ProductListActivity activity);
}
