package com.beebrainy.heady.ecommerce.server.components.ranking;

import com.beebrainy.heady.ecommerce.server.components.repo.IRepo;
import com.beebrainy.heady.ecommerce.server.components.repo.Repo;
import com.beebrainy.heady.ecommerce.server.models.ProductEntity;
import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import java.util.List;

import io.realm.RealmResults;

public class RankingBO implements IRanking {

    @Override
    public void addRanking(RankingEntity rankingEntity, List<Long> productIds) {
        IRepo repo = new Repo();
        RealmResults<ProductEntity> productEntities = repo.getProducts(productIds);
        rankingEntity.getProductEntities().addAll(productEntities);
        repo.addRanking(rankingEntity);
    }
}
