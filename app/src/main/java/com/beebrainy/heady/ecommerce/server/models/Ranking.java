package com.beebrainy.heady.ecommerce.server.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Ranking extends RealmObject {

    @PrimaryKey
    long id;
    String title;
    RealmList<Product> products;

    public Ranking(long id, String title, RealmList<Product> products) {
        this.id = id;
        this.title = title;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<Product> getProducts() {
        return products;
    }

    public void setProducts(RealmList<Product> products) {
        this.products = products;
    }
}
