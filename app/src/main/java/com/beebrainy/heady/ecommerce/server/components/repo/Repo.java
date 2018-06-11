package com.beebrainy.heady.ecommerce.server.components.repo;

import android.util.Log;

import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;
import com.beebrainy.heady.ecommerce.server.models.ProductEntity;
import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class Repo implements IRepo {

    @Override
    public RealmResults<CategoryEntity> getCategories() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CategoryEntity.class).findAll();
    }

    @Override
    public RealmResults<CategoryEntity> getCategories(List<Long> ids) {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<CategoryEntity> query = realm.where(CategoryEntity.class);
        for (Long id : ids) {
            query.equalTo("id", id).or();
        }
        RealmResults<CategoryEntity> realmResults = query.findAll();
        return realmResults;
    }

    @Override
    public RealmResults<CategoryEntity> getMainCategories() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CategoryEntity.class).isNotEmpty("subCategories").and().isEmpty
                ("productEntities").findAll();
    }

    public CategoryEntity getCategory(long id) {
        Realm realm = Realm.getDefaultInstance();
        CategoryEntity categoryEntity = realm.where(CategoryEntity.class).equalTo("id", id)
                .findFirst();
        return categoryEntity;
    }

    @Override
    public void deleteCategory(long id) {
        Realm realm = Realm.getDefaultInstance();
        final CategoryEntity ce = realm.where(CategoryEntity.class).equalTo("id", id).findFirst();
        Log.d(Repo.class.getSimpleName(), "Cat deletion:" + ce);
        if (ce != null) {
            realm.beginTransaction();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    ce.deleteFromRealm();
                    Log.d(Repo.class.getSimpleName(), "Success");
                }
            });
        } else {
            Log.d(Repo.class.getSimpleName(), "Fail");
        }
    }

    @Override
    public RealmList<CategoryEntity> getSubCategories(long categoryId) {
        Realm realm = Realm.getDefaultInstance();
        RealmList<CategoryEntity> categoryEntities = realm.where(CategoryEntity.class).equalTo
                ("id", categoryId).findFirst().getSubCategories();
        return categoryEntities;
    }

    @Override
    public RealmList<ProductEntity> getProducts(long categoryId) {
        Realm realm = Realm.getDefaultInstance();
        RealmList<ProductEntity> productEntities = realm.where(CategoryEntity.class).equalTo
                ("id", categoryId).findFirst().getProductEntities();
        return productEntities;
    }

    @Override
    public RealmResults<ProductEntity> getProducts(List<Long> productIds) {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<ProductEntity> query = realm.where(ProductEntity.class);
        for (Long prodId : productIds) {
            query.equalTo("id", prodId).or();
        }
        return query.findAll();
    }

    @Override
    public RealmResults<RankingEntity> getRankings() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RankingEntity> rankingEntity = realm.where(RankingEntity.class).findAll();
        return rankingEntity;
    }

    @Override
    public RankingEntity getRanking(long rankId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(RankingEntity.class).equalTo("id", rankId).findFirst();
    }

    @Override
    public void addRanking(final RankingEntity rankingEntity) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(rankingEntity);
        realm.commitTransaction();
    }

    @Override
    public void addCategory(final CategoryEntity categoryEntity) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(categoryEntity);
        Log.d(Repo.class.getSimpleName(), "Cat added:" + categoryEntity);
        realm.commitTransaction();
    }

    @Override
    public void addProduct(final long categoryId, final ProductEntity productEntity) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CategoryEntity categoryEntity = realm.where(CategoryEntity.class).equalTo("id",
                        categoryId).findFirst();
                if (categoryEntity != null) {
                    categoryEntity.getProductEntities().add(productEntity);
                }
            }
        });

    }


    @Override
    public void addChildCategory(final long parentId, final List<CategoryEntity> childCategories) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CategoryEntity parentCE = realm.where(CategoryEntity.class).equalTo("id", parentId)
                .findFirst();
        if (parentCE != null) {
            parentCE.getSubCategories().addAll(childCategories);
        }
        realm.commitTransaction();
    }
}
