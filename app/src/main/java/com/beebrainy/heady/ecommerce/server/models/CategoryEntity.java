package com.beebrainy.heady.ecommerce.server.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CategoryEntity extends RealmObject {

    @PrimaryKey
    long id;
    String name;
    RealmList<CategoryEntity> subCategories;
    RealmList<ProductEntity> productEntities;

    public CategoryEntity() {
    }

    public CategoryEntity(long id, String name, RealmList<CategoryEntity> subCategories, RealmList<ProductEntity> productEntities) {
        this.id = id;
        this.name = name;
        this.subCategories = subCategories;
        this.productEntities = productEntities;
    }

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

    public RealmList<CategoryEntity> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(RealmList<CategoryEntity> subCategories) {
        this.subCategories = subCategories;
    }

    public RealmList<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(RealmList<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }
}
