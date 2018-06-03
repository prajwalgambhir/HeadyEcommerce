package com.beebrainy.heady.ecommerce.server.components.repo;

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

    public CategoryEntity getCategory(long id) {
        Realm realm = Realm.getDefaultInstance();
        CategoryEntity categoryEntity = realm.where(CategoryEntity.class).equalTo("id", id)
                .findFirst();
        realm.close();
        return categoryEntity;
    }

    @Override
    public RealmList<CategoryEntity> getSubCategories(long categoryId) {
        Realm realm = Realm.getDefaultInstance();
        RealmList<CategoryEntity> categoryEntities = realm.where(CategoryEntity.class).and()
                .equalTo("id", categoryId).findFirst().getSubCategories();
        realm.close();
        return categoryEntities;
    }

    @Override
    public RealmList<ProductEntity> getProducts(long categoryId) {
        Realm realm = Realm.getDefaultInstance();
        RealmList<ProductEntity> productEntities = realm.where(CategoryEntity.class).equalTo
                ("id", categoryId).findFirst().getProductEntities();
        realm.close();
        return productEntities;
    }

    @Override
    public RealmResults<ProductEntity> getProducts(List<Long> productIds) {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<ProductEntity> query = realm.where(ProductEntity.class);
        for (Long prodId : productIds) {
            query.equalTo("id", prodId);
        }
        realm.close();
        return query.findAll();
    }

    @Override
    public RealmResults<RankingEntity> getRankings() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RankingEntity> rankingEntity = realm.where(RankingEntity.class).findAll();
        realm.close();
        return rankingEntity;
    }

    @Override
    public void addRanking(final RankingEntity rankingEntity) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(rankingEntity);
                realm.close();
            }
        });
    }

    public void addCategory(final CategoryEntity categoryEntity) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(categoryEntity);
                realm.close();
            }
        });

    }

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
                realm.close();
            }
        });

    }

    public void addChildCategory(final long parentId, final List<Long> childIds) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CategoryEntity categoryEntity = realm.where(CategoryEntity.class).equalTo("id",
                        parentId).findFirst();
                RealmQuery<CategoryEntity> query = realm.where(CategoryEntity.class);
                for (Long childId : childIds) {
                    query.equalTo("id", childId);
                }
                RealmResults<CategoryEntity> childCategories = query.findAll();
                categoryEntity.getSubCategories().addAll(childCategories);
                realm.close();
            }
        });
    }
}
