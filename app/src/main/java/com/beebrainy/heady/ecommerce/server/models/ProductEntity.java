package com.beebrainy.heady.ecommerce.server.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProductEntity extends RealmObject {

    @PrimaryKey
    long id;
    String name;
    Date dateAdded;
    Long viewCount;
    Long orderCount;
    Long shareCount;
    RealmList<VariantEntity> variantEntities = new RealmList<>();
    TaxEntity taxEntity;

    public ProductEntity() {
    }

    public ProductEntity(long id, String name, Date dateAdded, Long viewCount, Long orderCount,
                         Long shareCount, RealmList<VariantEntity> variantEntities, TaxEntity
                                 taxEntity) {
        this.id = id;
        this.name = name;
        this.dateAdded = dateAdded;
        this.viewCount = viewCount;
        this.orderCount = orderCount;
        this.shareCount = shareCount;
        this.variantEntities = variantEntities;
        this.taxEntity = taxEntity;
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

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Long getShareCount() {
        return shareCount;
    }

    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
    }

    public RealmList<VariantEntity> getVariantEntities() {
        return variantEntities;
    }

    public void setVariantEntities(RealmList<VariantEntity> variantEntities) {
        this.variantEntities = variantEntities;
    }

    public TaxEntity getTaxEntity() {
        return taxEntity;
    }

    public void setTaxEntity(TaxEntity taxEntity) {
        this.taxEntity = taxEntity;
    }
}
