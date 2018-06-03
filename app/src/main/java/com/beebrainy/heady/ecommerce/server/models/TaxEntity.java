package com.beebrainy.heady.ecommerce.server.models;

import io.realm.RealmObject;

public class TaxEntity extends RealmObject {

    String name;
    Double value;

    public TaxEntity() {

    }

    public TaxEntity(String name, Double value) {
        this.name = name;
        this.value = value;
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
