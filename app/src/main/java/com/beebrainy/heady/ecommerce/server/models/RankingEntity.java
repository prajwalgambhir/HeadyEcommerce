package com.beebrainy.heady.ecommerce.server.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RankingEntity extends RealmObject {

    @PrimaryKey
    long id;
    String title;
    RealmList<ProductEntity> productEntities = new RealmList<>();

    public RankingEntity() {
    }

    public RankingEntity(long id, String title, RealmList<ProductEntity> productEntities) {
        this.id = id;
        this.title = title;
        this.productEntities = productEntities;
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

    public RealmList<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(RealmList<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }

    @Override
    public String toString() {
        return "RankingEntity{" + "id=" + id + ", title='" + title + '\'' + ", productEntities="
                + productEntities + '}';
    }
}
