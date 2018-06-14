package com.beebrainy.heady.ecommerce.client.service;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Wrapper for {@link SharedPreferences}
 */
public class StorageService {

    private static final String SHARED_PREF_NAME = "HECOMMERCE";
    private SharedPreferences sp;

    public StorageService(Context context) {
        sp = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void putBoolean(String key, boolean flag) {
        sp.edit().putBoolean(key, flag).apply();
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }
}
