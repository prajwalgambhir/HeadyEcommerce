package com.beebrainy.heady.ecommerce.server.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class VariantEntity extends RealmObject {

    @PrimaryKey
    long id;
    String color;
    Integer size;
    Integer price;

    public VariantEntity() {
    }

    public VariantEntity(long id, String color, Integer size, Integer price) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
