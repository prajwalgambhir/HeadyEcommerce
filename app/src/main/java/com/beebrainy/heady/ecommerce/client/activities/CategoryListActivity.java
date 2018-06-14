package com.beebrainy.heady.ecommerce.client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.beebrainy.heady.ecommerce.R;
import com.beebrainy.heady.ecommerce.client.activities.di.CLAComponent;
import com.beebrainy.heady.ecommerce.client.activities.di.DaggerCLAComponent;
import com.beebrainy.heady.ecommerce.client.adapters.CategoryAdapter;
import com.beebrainy.heady.ecommerce.client.listeners.ItemClickListener;
import com.beebrainy.heady.ecommerce.server.components.category.ICategory;
import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CategoryListActivity extends BaseActivity {

    private static final String KEY_MAIN_CAT_ID = "KEY_MAIN_CAT_ID";
    public static final String KEY_CAT_ID = "KEY_CAT_ID";
    private boolean IS_MAIN_CAT = true;

    private RecyclerView rvCL;
    private List<CategoryEntity> categoryEntityList = new ArrayList<>();

    private ItemClickListener<CategoryEntity> listener = new ItemClickListener<CategoryEntity>() {
        @Override
        public void onItemClick(CategoryEntity categoryEntity) {
            onCategoryClicked(categoryEntity);
        }
    };

    private CLAComponent categoryComponent;
    @Inject
    ICategory category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        IS_MAIN_CAT = !getIntent().hasExtra(KEY_MAIN_CAT_ID);
        categoryComponent = DaggerCLAComponent.builder().build();
        categoryComponent.inject(this);
        getCategories();
        initView();
    }

    private void getCategories() {
        if (IS_MAIN_CAT) {
            categoryEntityList.addAll(category.getMainCategories());
        } else {
            long catId = getIntent().getLongExtra(KEY_MAIN_CAT_ID, -1);
            categoryEntityList.addAll(category.getSubCategory(catId));
        }
    }

    private void initView() {
        rvCL = findViewById(R.id.rvCL);
        RecyclerView.LayoutManager linerLayout = new LinearLayoutManager(this);
        rvCL.setLayoutManager(linerLayout);
        rvCL.setItemAnimator(new DefaultItemAnimator());
        CategoryAdapter ca = new CategoryAdapter(this, categoryEntityList, listener);
        rvCL.setAdapter(ca);
    }

    private void onCategoryClicked(CategoryEntity ce) {
        if (!ce.getSubCategories().isEmpty()) {
            Intent subCatIntent = new Intent(this, CategoryListActivity.class);
            subCatIntent.putExtra(KEY_MAIN_CAT_ID, ce.getId());
            startActivity(subCatIntent);
        } else if (ce.getProductEntities().size() > 0) {
            Intent prodIntent = new Intent(this, ProductListActivity.class);
            prodIntent.putExtra(KEY_CAT_ID, ce.getId());
            startActivity(prodIntent);
        }
    }

}
