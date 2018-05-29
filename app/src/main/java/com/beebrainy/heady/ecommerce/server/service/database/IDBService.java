package com.beebrainy.heady.ecommerce.server.service.database;

import com.beebrainy.heady.ecommerce.server.models.Category;
import com.beebrainy.heady.ecommerce.server.models.Product;
import com.beebrainy.heady.ecommerce.server.models.Ranking;

import io.realm.RealmResults;

public interface IDBService {

    RealmResults<Category> getCategories();

    RealmResults<Category> getSubCategories(Category category);

    RealmResults<Product> getProducts();

    RealmResults<Ranking> getRankings();
}
