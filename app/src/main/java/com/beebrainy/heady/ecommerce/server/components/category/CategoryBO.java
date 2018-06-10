package com.beebrainy.heady.ecommerce.server.components.category;

import com.beebrainy.heady.ecommerce.server.components.repo.IRepo;
import com.beebrainy.heady.ecommerce.server.components.repo.Repo;
import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;

import java.util.List;

import io.realm.RealmList;

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
        repo.addChildCategory(parentId, dupSubCategories);
    }

    @Override
    public List<CategoryEntity> getCategories() {
        IRepo repo = new Repo();
        return repo.getCategories();
    }

    @Override
    public CategoryEntity getCategory(long id) {
        IRepo repo = new Repo();
        return repo.getCategory(id);
    }

    @Override
    public RealmList<CategoryEntity> getMainCategories() {
        IRepo repo = new Repo();
        RealmList<CategoryEntity> mainCategories = new RealmList<>();
        mainCategories.addAll(repo.getMainCategories());
        return mainCategories;
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
