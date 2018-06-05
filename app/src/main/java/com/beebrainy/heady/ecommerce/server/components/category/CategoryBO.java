package com.beebrainy.heady.ecommerce.server.components.category;

import com.beebrainy.heady.ecommerce.server.components.repo.IRepo;
import com.beebrainy.heady.ecommerce.server.components.repo.Repo;
import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryBO implements ICategory {

    @Override
    public void addCategory(CategoryEntity categoryEntity) {
        IRepo repo = new Repo();
        repo.addCategory(categoryEntity);
    }

    @Override
    public void addSubCategory(long parentId, List<Long> childIds) {
        IRepo repo = new Repo();
        List<CategoryEntity> dupSubCategories = repo.getCategories(childIds);
        List<CategoryEntity> subCat = new ArrayList<>(dupSubCategories.size());
        for (CategoryEntity ce : dupSubCategories) {
            CategoryEntity categoryEntity = new CategoryEntity(ce);
            subCat.add(categoryEntity);
            repo.deleteCategory(ce.getId());
        }
        repo.addChildCategory(parentId, subCat);
    }

    @Override
    public CategoryEntity getCategory(long id) {
        IRepo repo = new Repo();
        return repo.getCategory(id);
    }

    @Override
    public List<CategoryEntity> getSubCategory(long pCategoryId) {
        IRepo repo = new Repo();
        return repo.getSubCategories(pCategoryId);
    }

    @Override
    public void deleteCategory(long id) {
        IRepo repo = new Repo();
        repo.deleteCategory(id);
    }
}
