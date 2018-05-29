package com.beebrainy.heady.ecommerce.server.service.database;

import com.beebrainy.heady.ecommerce.server.models.Category;
import com.beebrainy.heady.ecommerce.server.models.Product;
import com.beebrainy.heady.ecommerce.server.models.Ranking;

import io.realm.RealmResults;

public class DBService implements IDBService {

    @Override
    public RealmResults<Category> getCategories() {
        return null;
    }

    @Override
    public RealmResults<Category> getSubCategories(Category category) {
        return null;
    }

    @Override
    public RealmResults<Product> getProducts() {
        return null;
    }

    @Override
    public RealmResults<Ranking> getRankings() {
        return null;
    }
}
