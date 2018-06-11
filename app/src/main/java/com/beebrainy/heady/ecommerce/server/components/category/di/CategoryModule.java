package com.beebrainy.heady.ecommerce.server.components.category.di;

import com.beebrainy.heady.ecommerce.server.components.category.CategoryBO;
import com.beebrainy.heady.ecommerce.server.components.category.ICategory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Prajwal Gambhir on 11-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

@Module
public class CategoryModule {

    @Provides
    ICategory provideCategory() {
        return new CategoryBO();
    }

}
