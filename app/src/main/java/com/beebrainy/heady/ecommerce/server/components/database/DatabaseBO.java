package com.beebrainy.heady.ecommerce.server.components.database;

import android.content.Context;
import android.util.Log;

import com.beebrainy.heady.ecommerce.client.service.StorageService;
import com.beebrainy.heady.ecommerce.server.components.category.CategoryBO;
import com.beebrainy.heady.ecommerce.server.components.category.ICategory;
import com.beebrainy.heady.ecommerce.server.components.ranking.IRanking;
import com.beebrainy.heady.ecommerce.server.components.ranking.RankingBO;
import com.beebrainy.heady.ecommerce.server.models.CategoryEntity;
import com.beebrainy.heady.ecommerce.server.models.ProductEntity;
import com.beebrainy.heady.ecommerce.server.models.RankingEntity;
import com.beebrainy.heady.ecommerce.server.models.TaxEntity;
import com.beebrainy.heady.ecommerce.server.models.VariantEntity;
import com.beebrainy.heady.ecommerce.server.services.network.CallbackResponse;
import com.beebrainy.heady.ecommerce.server.services.network.INetworkService;
import com.beebrainy.heady.ecommerce.server.services.network.NetworkService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class DatabaseBO implements IDatabase {

    Context context;

    CallbackResponse callbackResponse = new CallbackResponse<String>() {
        @Override
        public void onResponse(String str) {
            try {
                JSONObject jo = new JSONObject(str);
                JSONArray jaCategories = jo.getJSONArray("categories");
                JSONArray jaRankings = jo.getJSONArray("rankings");
                for (int i = 0; i < jaCategories.length(); i++) {
                    JSONObject joCategory = jaCategories.getJSONObject(i);
                    CategoryEntity categoryEntity = categoryFromJson(joCategory);

                    JSONArray jaProducts = joCategory.getJSONArray("products");
                    JSONArray jaChildCategory = joCategory.getJSONArray("child_categories");
                    if (jaProducts.length() > 0) {
                        List<ProductEntity> prods = new ArrayList<>();
                        for (int j = 0; j < jaProducts.length(); j++) {
                            JSONObject joProduct = jaProducts.getJSONObject(j);
                            ProductEntity p = productFromJson(joProduct);
                            TaxEntity taxEntity = taxFromJson(joProduct.getJSONObject("taxEntity"));
                            p.setTaxEntity(taxEntity);
                            RealmList<VariantEntity> variantEntities = new RealmList<>();
                            JSONArray jaVariants = joProduct.getJSONArray("variantEntities");
                            for (int k = 0; k < jaVariants.length(); k++) {
                                VariantEntity variantEntity = variantFromJson(jaVariants
                                        .getJSONObject(k));
                                variantEntities.add(variantEntity);
                            }
                            p.setVariantEntities(variantEntities);
                            prods.add(p);
                        }
                    }
                    addCategory(categoryEntity);
                    if (jaChildCategory.length() > 0) {
                        addChildCategory(categoryEntity.getId(), jaChildCategory);
                    }
                }
                for (int r = 0; r < jaRankings.length(); r++) {
                    RankingEntity rankingEntity = new RankingEntity();
                    rankingEntity.setId(r);
                    rankingEntity.setTitle(jaRankings.getJSONObject(r).getString("ranking"));
                    addRanking(rankingEntity, jaRankings.getJSONObject(r).getJSONArray("products"));
                }
            } catch (JSONException e) {
                StorageService storageService = new StorageService(context);
                storageService.putBoolean(IDatabase.DB_LOADED, false);
                e.printStackTrace();
                return;
            } catch (ParseException e) {
                StorageService storageService = new StorageService(context);
                storageService.putBoolean(IDatabase.DB_LOADED, false);
                e.printStackTrace();
            }
            StorageService storageService = new StorageService(context);
            storageService.putBoolean(IDatabase.DB_LOADED, true);
        }
    };

    @Override
    public void clearDb(Context context) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        realm.close();
    }

    public void downloadDatabase(final Context context) {
        this.context = context;
        INetworkService iNetworkService = new NetworkService();
        iNetworkService.getRequest(JSON_URL, callbackResponse);
    }

    private void addCategory(CategoryEntity categoryEntity) {
        ICategory category = new CategoryBO();
        category.addCategory(categoryEntity);
    }

    private void addChildCategory(long parentId, JSONArray jaChildIds) throws JSONException {
        //call bo add child category
        List<Long> childIds = new ArrayList<>(jaChildIds.length());
        for (int i = 0; i < jaChildIds.length(); i++) {
            childIds.add(jaChildIds.getLong(i));
        }
        ICategory category = new CategoryBO();
        category.addSubCategory(parentId, childIds);
    }

    private void addRanking(RankingEntity rankingEntity, JSONArray products) throws JSONException {
        IRanking ranking = new RankingBO();
        List<Long> productIds = new ArrayList<>(products.length());
        for (int i = 0; i < products.length(); i++) {
            productIds.add(products.getLong(i));
        }
        ranking.addRanking(rankingEntity, productIds);
    }

    private ProductEntity productFromJson(JSONObject joProduct) throws JSONException,
            ParseException {
        ProductEntity p = new ProductEntity();
        p.setId(joProduct.getInt("id"));
        p.setName(joProduct.getString("name"));
        String dateStr = joProduct.getString("date_added").replace("T", " ");
        Log.d(DatabaseBO.class.getSimpleName(), joProduct.getString("name") + " " + dateStr);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        p.setDateAdded(sf.parse(dateStr.split(".")[0]));
        return p;
    }

    private TaxEntity taxFromJson(JSONObject joTax) throws JSONException {
        return new TaxEntity(joTax.getString("name"), joTax.getDouble("value"));
    }

    private VariantEntity variantFromJson(JSONObject joVariant) throws JSONException {
        VariantEntity variantEntity = new VariantEntity();
        variantEntity.setId(joVariant.getInt("id"));
        variantEntity.setColor(joVariant.getString("color"));
        variantEntity.setSize(joVariant.getInt("size"));
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
