package com.beebrainy.heady.ecommerce.server.components.category.di;

import com.beebrainy.heady.ecommerce.client.activities.CategoryListActivity;
import com.beebrainy.heady.ecommerce.server.components.category.ICategory;
import com.beebrainy.heady.ecommerce.server.components.database.DatabaseDownloaderBO;

import dagger.Component;

/**
 * Created by Prajwal Gambhir on 11-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Component(modules = CategoryModule.class)
public interface CategoryComponent {

    ICategory provideCategory();

    void inject(CategoryListActivity categoryListActivity);

    void inject(DatabaseDownloaderBO databaseDownloader);
}
