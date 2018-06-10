package com.beebrainy.heady.ecommerce.server.components.ranking;

import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import java.util.Map;

import io.realm.RealmList;

public interface IRanking {


    /**
     * @param rankingEntity
     * @param mapProductIdCount @key - ProductId,@value - count
     */
    void addRanking(RankingEntity rankingEntity, Map<Long, Long> mapProductIdCount);

    RealmList<RankingEntity> getRankings();

    RankingEntity getRankingEntity(long id);
}
