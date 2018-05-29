package com.beebrainy.heady.ecommerce.server.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Category extends RealmObject {

    @PrimaryKey
    long id;
    String name;
    RealmList<Category> subCategories;
    RealmList<Product> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(RealmList<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public RealmList<Product> getProducts() {
        return products;
    }

    public void setProducts(RealmList<Product> products) {
        this.products = products;
    }
}
