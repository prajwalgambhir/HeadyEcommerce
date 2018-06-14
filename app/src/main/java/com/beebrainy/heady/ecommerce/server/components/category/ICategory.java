package com.beebrainy.heady.ecommerce.server.components.category;

import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;

import java.util.List;

/**
 * Business Logic for Category component.
 */
public interface ICategory {

    /**
     * Adds Category. Replaces if already exists with same ID
     *
     * @param categoryEntity
     */
    void addCategory(CategoryEntity categoryEntity);

    /**
     * Adds subcategory for the Parent category by its Id.
     *
     * @param parentId - Parent Category ID.
     * @param childIds - Child Category ID.
     */
    void addSubCategory(long parentId, List<Long> childIds);

    /**
     * Fetches all the Categories
     *
     * @return list of {@link CategoryEntity}
     */
    List<CategoryEntity> getCategories();

    /**
     * Fetches Category by ID.
     *
     * @param id - Category ID
     * @return - {@link CategoryEntity}
     */
    CategoryEntity getCategory(long id);

    /**
     * Fetches list of categories with subcategories and zero product list.
     *
     * @return - list of {@link CategoryEntity}
     */
    List<CategoryEntity> getMainCategories();

    /**
     * Fetches Category having Parent with the passed ID.
     *
     * @param pCategoryId - Parent Category Id
     * @return - list of {@link CategoryEntity}
     */
    List<CategoryEntity> getSubCategory(long pCategoryId);

    /**
     * Deletes Category with the passed ID.
     *
     * @param id - Categories ID
     */
    void deleteCategory(long id);
}
