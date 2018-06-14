package com.beebrainy.heady.ecommerce.server.components.database;

import android.content.Context;
import android.util.Log;

import com.beebrainy.heady.ecommerce.client.service.StorageService;
import com.beebrainy.heady.ecommerce.server.components.category.ICategory;
import com.beebrainy.heady.ecommerce.server.components.database.di.DBDownloaderComponent;
import com.beebrainy.heady.ecommerce.server.components.database.di.DaggerDBDownloaderComponent;
import com.beebrainy.heady.ecommerce.server.components.ranking.IRanking;
import com.beebrainy.heady.ecommerce.server.components.repo.IRepo;
import com.beebrainy.heady.ecommerce.server.contracts.CallbackResponse;
import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;
import com.beebrainy.heady.ecommerce.server.models.ProductEntity;
import com.beebrainy.heady.ecommerce.server.models.RankingEntity;
import com.beebrainy.heady.ecommerce.server.models.TaxEntity;
import com.beebrainy.heady.ecommerce.server.models.VariantEntity;
import com.beebrainy.heady.ecommerce.server.services.network.INetworkService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.realm.RealmList;

public class DatabaseDownloaderBO implements IDatabaseDownloader {

    private Context context;
    private DBDownloaderComponent component;
    @Inject
    INetworkService iNetworkService;
    @Inject
    ICategory category;
    @Inject
    IRanking ranking;
    @Inject
    IRepo repo;

    private CallbackResponse<Boolean> callbackResponse;

    //Handles parsing of the database in JSON String
    private CallbackResponse nwCallbackResponse = new CallbackResponse<String>() {
        @Override
        public void onResponse(String str) {
            try {
                JSONObject jo = new JSONObject(str);
                JSONArray jaCategories = jo.getJSONArray("categories");
                //Add Main categories
                for (int i = 0; i < jaCategories.length(); i++) {
                    JSONObject joCategory = jaCategories.getJSONObject(i);
                    CategoryEntity categoryEntity = categoryFromJson(joCategory);
                    JSONArray jaProducts = joCategory.getJSONArray("products");
                    if (jaProducts.length() > 0) {
                        RealmList<ProductEntity> prods = new RealmList<>();
                        for (int j = 0; j < jaProducts.length(); j++) {
                            JSONObject joProduct = jaProducts.getJSONObject(j);
                            ProductEntity p = productFromJson(joProduct);
                            TaxEntity taxEntity = taxFromJson(joProduct.getJSONObject("tax"));
                            p.setTaxEntity(taxEntity);
                            RealmList<VariantEntity> variantEntities = new RealmList<>();
                            JSONArray jaVariants = joProduct.getJSONArray("variants");
                            for (int k = 0; k < jaVariants.length(); k++) {
                                VariantEntity variantEntity = variantFromJson(p.getId(),
                                        jaVariants.getJSONObject(k));
                                variantEntities.add(variantEntity);
                            }
                            p.setVariantEntities(variantEntities);
                            prods.add(p);
                        }
                        categoryEntity.setProductEntities(prods);
                    }
                    addCategory(categoryEntity);
                }

                //add sub categories
                for (int i = 0; i < jaCategories.length(); i++) {
                    JSONObject joCategory = jaCategories.getJSONObject(i);
                    CategoryEntity categoryEntity = categoryFromJson(joCategory);
                    JSONArray jaChildCategory = joCategory.getJSONArray("child_categories");
                    if (jaChildCategory.length() > 0) {
                        addChildCategory(categoryEntity.getId(), jaChildCategory);
                    }
                }

                //Add rankings
                JSONArray jaRankings = jo.getJSONArray("rankings");
                for (int r = 0; r < jaRankings.length(); r++) {
                    RankingEntity rankingEntity = new RankingEntity();
                    rankingEntity.setId(r);
                    rankingEntity.setTitle(jaRankings.getJSONObject(r).getString("ranking"));
                    addRanking(rankingEntity, jaRankings.getJSONObject(r).getJSONArray("products"));
                }

                callbackResponse.onResponse(true);
            } catch (JSONException e) {
                StorageService storageService = new StorageService(context);
                storageService.putBoolean(IDatabaseDownloader.KEY_DB_LOADED, false);
                callbackResponse.onResponse(false);
                e.printStackTrace();
                return;
            } catch (ParseException e) {
                StorageService storageService = new StorageService(context);
                storageService.putBoolean(IDatabaseDownloader.KEY_DB_LOADED, false);
                callbackResponse.onResponse(false);
                e.printStackTrace();
                return;
            }
            StorageService storageService = new StorageService(context);
            storageService.putBoolean(IDatabaseDownloader.KEY_DB_LOADED, true);
        }
    };

    public DatabaseDownloaderBO() {
        component = DaggerDBDownloaderComponent.builder().build();
        component.inject(this);
    }

    @Override
    public void downloadDatabase(final Context context, CallbackResponse<Boolean>
            callbackResponse) {
        Log.d(DatabaseDownloaderBO.class.getSimpleName(), "Inside constructor");
        this.context = context;
        this.callbackResponse = callbackResponse;
        iNetworkService.getRequest(JSON_URL, nwCallbackResponse);
    }

    @Override
    public void clearDb() {
        repo.clearDB();
    }

    private void addCategory(CategoryEntity categoryEntity) {
        category.addCategory(categoryEntity);
    }

    private void addChildCategory(long parentId, JSONArray jaChildIds) throws JSONException {
        List<Long> childIds = new ArrayList<>(jaChildIds.length());
        for (int i = 0; i < jaChildIds.length(); i++) {
            childIds.add(jaChildIds.getLong(i));
        }
        category.addSubCategory(parentId, childIds);
    }

    private void addRanking(RankingEntity rankingEntity, JSONArray products) throws JSONException {
        Map<Long, Long> mapProductIdCount = new HashMap<>(products.length());
        String title = rankingEntity.getTitle();
        String countTitle = "";
        switch (title) {
            case "Most Viewed Products": {
                countTitle = "view_count";
                break;
            }
            case "Most OrdeRed Products": {
                countTitle = "order_count";
                break;
            }
            case "Most ShaRed Products": {
                countTitle = "shares";
                break;
            }
        }
        for (int i = 0; i < products.length(); i++) {
            mapProductIdCount.put(products.getJSONObject(i).getLong("id"), products.getJSONObject
                    (i).getLong(countTitle));
        }
        ranking.addRanking(rankingEntity, mapProductIdCount);
    }

    private ProductEntity productFromJson(JSONObject joProduct) throws JSONException,
            ParseException {
        ProductEntity p = new ProductEntity();
        p.setId(joProduct.getInt("id"));
        p.setName(joProduct.getString("name"));
        String dateStr = joProduct.getString("date_added");
        Log.d(DatabaseDownloaderBO.class.getSimpleName(), joProduct.getString("name") + " " +
                dateStr);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        p.setDateAdded(sf.parse(dateStr.replaceAll("Z$", "+0000")));
        return p;
    }

    private TaxEntity taxFromJson(JSONObject joTax) throws JSONException {
        return new TaxEntity(joTax.getString("name"), joTax.getDouble("value"));
    }

    private VariantEntity variantFromJson(long productId, JSONObject joVariant) throws
            JSONException {
        VariantEntity variantEntity = new VariantEntity();
        variantEntity.setId(joVariant.getInt("id"));
        variantEntity.setColor(joVariant.getString("color"));
        try {
            variantEntity.setSize(joVariant.getInt("size"));
        } catch (JSONException js) {
            variantEntity.setSize(null);
        }
        variantEntity.setPrice(joVariant.getInt("price"));
        return variantEntity;
    }

    private CategoryEntity categoryFromJson(JSONObject joCategory) throws JSONException {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(joCategory.getInt("id"));
        categoryEntity.setName(joCategory.getString("name"));
        return categoryEntity;
    }
}
