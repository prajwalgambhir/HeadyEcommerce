package com.beebrainy.heady.ecommerce.server.components.category;

import android.util.Log;

import com.beebrainy.heady.ecommerce.server.components.category.di.CategoryComponent;
import com.beebrainy.heady.ecommerce.server.components.category.di.DaggerCategoryComponent;
import com.beebrainy.heady.ecommerce.server.components.repo.IRepo;
import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CategoryBO implements ICategory {

    private CategoryComponent component;
    @Inject
    IRepo repo;

    public CategoryBO() {
        component = DaggerCategoryComponent.builder().build();
        component.inject(this);
        Log.d("LOG", "Inside CategoryBO constructor");
        Log.d("LOG", repo.toString());
    }

    @Override
    public void addCategory(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (categoryEntity.getId() < 0) {
            throw new IllegalStateException("Category id cannot be less than 0");
        }
        repo.addCategory(categoryEntity);
    }

    @Override
    public void addSubCategory(long parentId, List<Long> childIds) {
        if (parentId < 0) {
            throw new IllegalArgumentException("Invalid category id supplied");
        }
        if (childIds == null) {
            throw new IllegalArgumentException("Child Ids cannot be null");
        }
        List<CategoryEntity> dupSubCategories = repo.getCategories(childIds);
        repo.addChildCategory(parentId, dupSubCategories);
    }

    @Override
    public List<CategoryEntity> getCategories() {
        return repo.getCategories();
    }

    @Override
    public CategoryEntity getCategory(long id) {
        return repo.getCategory(id);
    }

    @Override
    public List<CategoryEntity> getMainCategories() {
        List<CategoryEntity> mainCategories = new ArrayList<>(repo.getMainCategories());
        List<CategoryEntity> temp = new ArrayList<>(mainCategories);
        for (CategoryEntity ce : mainCategories) {
            for (CategoryEntity sub : ce.getSubCategories()) {
                temp.remove(sub);
            }
        }
        return temp;
    }

    @Override
    public List<CategoryEntity> getSubCategory(long pCategoryId) {
        return repo.getSubCategories(pCategoryId);
    }

    @Override
    public void deleteCategory(long id) {
        repo.deleteCategory(id);
    }
}
