package com.beebrainy.heady.ecommerce.client.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.beebrainy.heady.ecommerce.R;
import com.beebrainy.heady.ecommerce.client.activities.di.DaggerPLAComponent;
import com.beebrainy.heady.ecommerce.client.activities.di.PLAComponent;
import com.beebrainy.heady.ecommerce.client.adapters.ProductAdapter;
import com.beebrainy.heady.ecommerce.client.listeners.ItemClickListener;
import com.beebrainy.heady.ecommerce.server.components.category.ICategory;
import com.beebrainy.heady.ecommerce.server.components.ranking.IRanking;
import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;
import com.beebrainy.heady.ecommerce.server.models.ProductEntity;
import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class ProductListActivity extends BaseActivity {

    private List<ProductEntity> productEntities = new ArrayList<>();
    private RecyclerView rvPL;
    private PLAComponent component;
    @Inject
    ICategory category;
    @Inject
    IRanking ranking;

    private ItemClickListener<ProductEntity> listener = new ItemClickListener<ProductEntity>() {
        @Override
        public void onItemClick(ProductEntity productEntity) {
            onProductSelected(productEntity);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        initView();
        component = DaggerPLAComponent.builder().build();
        component.inject(this);
        if (getIntent().hasExtra(CategoryListActivity.KEY_CAT_ID)) {
            long catId = getIntent().getLongExtra(CategoryListActivity.KEY_CAT_ID, -1);
            getProductsByCategory(catId);
        } else if (getIntent().hasExtra(RankingActivity.KEY_RANKING_ID)) {
            long rankId = getIntent().getLongExtra(RankingActivity.KEY_RANKING_ID, -1);
            getProductsByRanking(rankId);
        } else {
            Toast.makeText(this, "Wrong category/ranking selected", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        rvPL.setItemAnimator(new DefaultItemAnimator());
        rvPL.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        rvPL.addItemDecoration(itemDecoration);
        ProductAdapter pa = new ProductAdapter(this, productEntities, listener);
        rvPL.setAdapter(pa);
    }

    private void initView() {
        rvPL = findViewById(R.id.rvPL);
    }

    private void getProductsByCategory(long catId) {
        if (catId != -1) {
            CategoryEntity ce = category.getCategory(catId);
            productEntities.addAll(ce.getProductEntities());
        }
    }

    private void getProductsByRanking(long rankId) {
        if (rankId != -1) {
            RankingEntity re = ranking.getRankingEntity(rankId);
            productEntities.addAll(re.getProductEntities());
            sortProductsBy(re.getTitle());
        }
    }

    private void sortProductsBy(final String title) {
        Collections.sort(productEntities, new Comparator<ProductEntity>() {
            @Override
            public int compare(ProductEntity o1, ProductEntity o2) {
                switch (title) {
                    case "Most Viewed Products": {
                        return o2.getViewCount().compareTo(o1.getViewCount());
                    }
                    case "Most OrdeRed Products": {
                        return o2.getOrderCount().compareTo(o1.getOrderCount());
                    }
                    case "Most ShaRed Products": {
                        return o2.getShareCount().compareTo(o1.getShareCount());
                    }
                }
                return 0;
            }
        });
    }

    private void onProductSelected(ProductEntity productEntity) {
        Toast.makeText(this, productEntity.toString(), Toast.LENGTH_LONG).show();
    }
}
