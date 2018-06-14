package com.beebrainy.heady.ecommerce.server.components.ranking;

import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import java.util.Map;

import io.realm.RealmList;

/**
 * Business logic for Ranking component.
 */
public interface IRanking {

    /**
     * Adds Ranking to the DB. Updates the Products count(View, Order or Share) based on the
     * Ranking.
     *
     * @param rankingEntity     - {@link RankingEntity}
     * @param mapProductIdCount @Key - ProductId, @Value - count
     */
    void addRanking(RankingEntity rankingEntity, Map<Long, Long> mapProductIdCount);

    /**
     * Fetches list of Rankings.
     *
     * @return - list of {@link RankingEntity} as {@see RealmList}
     */
    RealmList<RankingEntity> getRankings();

    /**
     * Fetches Ranking by the passed ID.
     *
     * @param id - ID of the Ranking to be fetched
     * @return - {@link RankingEntity}
     */
    RankingEntity getRankingEntity(long id);
}
