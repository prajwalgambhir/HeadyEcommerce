package com.beebrainy.heady.ecommerce.server.components.ranking;

import com.beebrainy.heady.ecommerce.server.components.repo.IRepo;
import com.beebrainy.heady.ecommerce.server.components.repo.Repo;
import com.beebrainy.heady.ecommerce.server.models.ProductEntity;
import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import java.util.ArrayList;
import java.util.Map;

import io.realm.RealmList;
import io.realm.RealmResults;

public class RankingBO implements IRanking {

    @Override
    public void addRanking(RankingEntity rankingEntity, Map<Long, Long> mapProductIdCount) {
        IRepo repo = new Repo();
        RealmResults<ProductEntity> productEntities = repo.getProducts(new ArrayList<>
                (mapProductIdCount.keySet()));
        RealmList<ProductEntity> rlProductList = new RealmList<>();
        for (ProductEntity pe : productEntities) {
            ProductEntity temp = new ProductEntity(pe.getId(), pe.getName(), pe.getDateAdded(),
                    pe.getViewCount(), pe.getOrderCount(), pe.getShareCount(), pe
                    .getVariantEntities(), pe.getTaxEntity());
            long count = mapProductIdCount.get(pe.getId());
            switch (rankingEntity.getTitle()) {
                case "Most Viewed Products": {
                    temp.setViewCount(count);
                    break;
                }
                case "Most OrdeRed Products": {
                    temp.setOrderCount(count);
                    break;
                }
                case "Most ShaRed Products": {
                    temp.setShareCount(count);
                    break;
                }
            }
            rlProductList.add(temp);
        }
        rankingEntity.getProductEntities().addAll(rlProductList);

        repo.addRanking(rankingEntity);
    }

    @Override
    public RealmList<RankingEntity> getRankings() {
        IRepo repo = new Repo();
        RealmList<RankingEntity> list = new RealmList<>();
        for (RankingEntity re : repo.getRankings()) {
            list.add(re);
        }
        return list;
    }

    @Override
    public RankingEntity getRankingEntity(long id) {
        IRepo repo = new Repo();
        return repo.getRanking(id);
    }
}
