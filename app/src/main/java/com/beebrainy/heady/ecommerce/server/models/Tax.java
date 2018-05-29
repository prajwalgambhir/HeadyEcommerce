package com.beebrainy.heady.ecommerce.server.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Tax extends RealmObject {

    @PrimaryKey
    long id;
    String name;
    Double value;

    public Tax(long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
