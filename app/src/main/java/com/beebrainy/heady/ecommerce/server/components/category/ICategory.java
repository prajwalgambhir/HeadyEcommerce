package com.beebrainy.heady.ecommerce.server.components.category;

import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;

import java.util.List;

import io.realm.RealmList;

public interface ICategory {

    void addCategory(CategoryEntity categoryEntity);

    void addSubCategory(long parentId, List<Long> childIds);

    List<CategoryEntity> getCategories();

    CategoryEntity getCategory(long id);

    RealmList<CategoryEntity> getMainCategories();

    List<CategoryEntity> getSubCategory(long pCategoryId);

    void deleteCategory(long id);
}
