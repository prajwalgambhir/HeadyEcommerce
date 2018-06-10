package com.beebrainy.heady.ecommerce.client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.beebrainy.heady.ecommerce.R;
import com.beebrainy.heady.ecommerce.client.adapters.CategoryAdapter;
import com.beebrainy.heady.ecommerce.client.listeners.ItemClickListener;
import com.beebrainy.heady.ecommerce.server.components.category.CategoryBO;
import com.beebrainy.heady.ecommerce.server.components.category.ICategory;
import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        initView();
        IS_MAIN_CAT = !getIntent().hasExtra(KEY_MAIN_CAT_ID);
        getCategories();
        Log.d(CategoryListActivity.class.getSimpleName(), categoryEntityList.size() + "");
        RecyclerView.LayoutManager linerLayout = new LinearLayoutManager(this);
        rvCL.setLayoutManager(linerLayout);
        rvCL.setItemAnimator(new DefaultItemAnimator());
        CategoryAdapter<CategoryEntity> ca = new CategoryAdapter(this, categoryEntityList,
                listener);
        rvCL.setAdapter(ca);
        rvCL.getAdapter().notifyDataSetChanged();
    }

    private void getCategories() {
        ICategory category = new CategoryBO();
        if (IS_MAIN_CAT) {
            categoryEntityList.addAll(category.getMainCategories());
        } else {
            long catId = getIntent().getLongExtra(KEY_MAIN_CAT_ID, -1);
            categoryEntityList.addAll(category.getSubCategory(catId));
        }
    }

    private void initView() {
        rvCL = findViewById(R.id.rvCL);
    }

    private void onCategoryClicked(CategoryEntity ce) {
        if (IS_MAIN_CAT) {
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
