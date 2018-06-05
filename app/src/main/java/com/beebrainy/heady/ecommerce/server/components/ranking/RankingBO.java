package com.beebrainy.heady.ecommerce.server.components.ranking;

import com.beebrainy.heady.ecommerce.server.components.repo.IRepo;
import com.beebrainy.heady.ecommerce.server.components.repo.Repo;
import com.beebrainy.heady.ecommerce.server.models.ProductEntity;
import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import java.util.ArrayList;
import java.util.Map;

import io.realm.RealmResults;

public class RankingBO implements IRanking {

    @Override
    public void addRanking(RankingEntity rankingEntity, Map<Long, Long> mapProductIdCount) {
        IRepo repo = new Repo();
        RealmResults<ProductEntity> productEntities = repo.getProducts(new ArrayList<>
                (mapProductIdCount.keySet()));
        for (ProductEntity pe : productEntities) {
            long count = mapProductIdCount.get(pe.getId());
            switch (rankingEntity.getTitle()) {
                case "Most Viewed Products": {
                    pe.setViewCount(count);
                    break;
                }
                case "Most OrdeRed Products": {
                    pe.setOrderCount(count);
                    break;
                }
                case "Most ShaRed Products": {
                    pe.setShareCount(count);
                    break;
                }
            }
        }
        rankingEntity.getProductEntities().addAll(productEntities);
        repo.addRanking(rankingEntity);
    }
}
