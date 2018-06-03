package com.beebrainy.heady.ecommerce.server.components.ranking;

import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import java.util.List;

public interface IRanking {

    void addRanking(RankingEntity rankingEntity, List<Long> productIds);
}
