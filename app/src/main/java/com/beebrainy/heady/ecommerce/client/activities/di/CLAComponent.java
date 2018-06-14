package com.beebrainy.heady.ecommerce.client.activities.di;

import com.beebrainy.heady.ecommerce.client.activities.CategoryListActivity;
import com.beebrainy.heady.ecommerce.server.components.category.di.CategoryModule;
import com.beebrainy.heady.ecommerce.server.components.database.di.DBDownloaderModule;

import dagger.Component;

/**
 * Created by Prajwal Gambhir on 13-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Component(modules = {CategoryModule.class, DBDownloaderModule.class})
public interface CLAComponent {

    void inject(CategoryListActivity cla);
}
