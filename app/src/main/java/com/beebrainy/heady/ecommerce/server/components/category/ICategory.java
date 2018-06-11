package com.beebrainy.heady.ecommerce.server.components.category;

import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;

import java.util.List;

public interface ICategory {

    void addCategory(CategoryEntity categoryEntity);

    void addSubCategory(long parentId, List<Long> childIds);

    List<CategoryEntity> getCategories();

    CategoryEntity getCategory(long id);

    List<CategoryEntity> getMainCategories();

    List<CategoryEntity> getSubCategory(long pCategoryId);

    void deleteCategory(long id);
}
