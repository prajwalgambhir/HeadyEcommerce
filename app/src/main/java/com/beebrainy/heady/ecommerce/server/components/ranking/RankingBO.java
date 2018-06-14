package com.beebrainy.heady.ecommerce.server.components.ranking;

import com.beebrainy.heady.ecommerce.server.components.ranking.di.DaggerRankingComponent;
import com.beebrainy.heady.ecommerce.server.components.ranking.di.RankingComponent;
import com.beebrainy.heady.ecommerce.server.components.repo.IRepo;
import com.beebrainy.heady.ecommerce.server.models.ProductEntity;
import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import io.realm.RealmList;
import io.realm.RealmResults;

public class RankingBO implements IRanking {

    private RankingComponent rankingComponent;
    @Inject
    IRepo repo;

    public RankingBO() {
        rankingComponent = DaggerRankingComponent.builder().build();
        rankingComponent.inject(this);
    }

    @Override
    public void addRanking(RankingEntity rankingEntity, Map<Long, Long> mapProductIdCount) {
        if (rankingEntity == null) {
            throw new IllegalArgumentException("Ranking entity cannot be null.");
        }
        if (mapProductIdCount == null) {
            throw new IllegalArgumentException("Products cannot be null.");
        }
        RealmResults<ProductEntity> productEntities = repo.getProducts(new ArrayList<>
                (mapProductIdCount.keySet()));
        RealmList<ProductEntity> rlProductList = new RealmList<>();
        for (ProductEntity pe : productEntities) {
            ProductEntity productEntity = new ProductEntity(pe.getId(), pe.getName(), pe
                    .getDateAdded(), pe.getViewCount(), pe.getOrderCount(), pe.getShareCount(),
                    pe.getVariantEntities(), pe.getTaxEntity());
            long count = mapProductIdCount.get(pe.getId());
            switch (rankingEntity.getTitle()) {
                case "Most Viewed Products": {
                    productEntity.setViewCount(count);
                    break;
                }
                case "Most OrdeRed Products": {
                    productEntity.setOrderCount(count);
                    break;
                }
                case "Most ShaRed Products": {
                    productEntity.setShareCount(count);
                    break;
                }
            }
            rlProductList.add(productEntity);
        }
        rankingEntity.getProductEntities().addAll(rlProductList);
        repo.addRanking(rankingEntity);
    }

    @Override
    public RealmList<RankingEntity> getRankings() {
        RealmList<RankingEntity> list = new RealmList<>();
        for (RankingEntity re : repo.getRankings()) {
            list.add(re);
        }
        return list;
    }

    @Override
    public RankingEntity getRankingEntity(long id) {
        return repo.getRanking(id);
    }
}
