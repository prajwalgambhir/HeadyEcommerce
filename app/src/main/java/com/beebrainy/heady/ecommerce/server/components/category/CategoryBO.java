package com.beebrainy.heady.ecommerce.server.components.category;

import com.beebrainy.heady.ecommerce.server.components.repo.IRepo;
import com.beebrainy.heady.ecommerce.server.components.repo.Repo;
import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;

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
        repo.addChildCategory(parentId, childIds);
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
}
