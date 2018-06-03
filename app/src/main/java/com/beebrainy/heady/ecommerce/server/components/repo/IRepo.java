package com.beebrainy.heady.ecommerce.server.components.repo;

import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;
import com.beebrainy.heady.ecommerce.server.models.ProductEntity;
import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

public interface IRepo {

    void addCategory(CategoryEntity categoryEntity);

    RealmResults<CategoryEntity> getCategories();

    CategoryEntity getCategory(long id);

    RealmList<CategoryEntity> getSubCategories(long categoryId);

    RealmList<ProductEntity> getProducts(long categoryId);

    RealmResults<ProductEntity> getProducts(List<Long> productIds);

    RealmResults<RankingEntity> getRankings();

    void addRanking(RankingEntity rankingEntity);

    void addChildCategory(long parentId, List<Long> childIds);
}
