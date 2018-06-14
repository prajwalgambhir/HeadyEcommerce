package com.beebrainy.heady.ecommerce.server.components.repo;

import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;
import com.beebrainy.heady.ecommerce.server.models.ProductEntity;
import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Handles all the Database CRUD operations.
 */
public interface IRepo {

    /**
     * adds the Category into DB.
     *
     * @param categoryEntity - {@link CategoryEntity}
     */
    void addCategory(CategoryEntity categoryEntity);

    /**
     * Fetches all the Categories in the DB.
     *
     * @return - list of {@link CategoryEntity} in the form of {@see RealmResults}
     */
    RealmResults<CategoryEntity> getCategories();

    /**
     * Fetches Categories by ID
     *
     * @param ids - Category Ids
     * @return - list of {@link CategoryEntity} in the form of {@see RealmResults}
     */
    RealmResults<CategoryEntity> getCategories(List<Long> ids);

    /**
     * Fetches only those categories which have sub categories and zero products.
     *
     * @return - list of {@link CategoryEntity} in the form of {@see RealmResults}
     */
    RealmResults<CategoryEntity> getMainCategories();

    /**
     * Fetches single Category by ID.
     *
     * @param id - Category Id
     * @return - {@link CategoryEntity}
     */
    CategoryEntity getCategory(long id);

    /**
     * Deletes the Category from DB by ID.
     *
     * @param id
     */
    void deleteCategory(long id);

    /**
     * Fetches sub categories by their parent categories ID.
     *
     * @param categoryId - Parent ID
     * @return - list of {@link CategoryEntity} in the form of {@see RealmResults}
     */
    RealmList<CategoryEntity> getSubCategories(long categoryId);

    /**
     * Fetches list of Products under category with ID
     *
     * @param categoryId - Category ID
     * @return - list of {@link ProductEntity} in the form of {@see RealmResults}
     */
    RealmList<ProductEntity> getProducts(long categoryId);

    /**
     * Fetches list of Products by their IDs.
     *
     * @param productIds - Product Ids
     * @return - list of {@link ProductEntity} in the form of {@see RealmResults}
     */
    RealmResults<ProductEntity> getProducts(List<Long> productIds);

    /**
     * Fetches list of Ranking Categories
     *
     * @return - list of {@link RankingEntity} in the form of {@see RealmResults}
     */
    RealmResults<RankingEntity> getRankings();

    /**
     * Fetches Ranking by its ID.
     *
     * @param rankId - Ranking's ID
     * @return - {@link RankingEntity}
     */
    RankingEntity getRanking(long rankId);

    /**
     * Adds Ranking to DB.
     *
     * @param rankingEntity - {@link RankingEntity}
     */
    void addRanking(RankingEntity rankingEntity);

    /**
     * Adds the Product under the category for the passed ID.
     *
     * @param categoryId    - ID of the {@link CategoryEntity}
     * @param productEntity - {@link ProductEntity} to be added
     */
    void addProduct(long categoryId, ProductEntity productEntity);

    /**
     * Adds the list of Categories under the category for the passed ID.
     *
     * @param parentId        - Id for the Parent {@link CategoryEntity}
     * @param childCategories - list of {@link CategoryEntity}
     */
    void addChildCategory(long parentId, List<CategoryEntity> childCategories);

    /**
     * Clears the DB.
     */
    void clearDB();
}
