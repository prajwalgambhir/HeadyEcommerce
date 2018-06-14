package com.beebrainy.heady.ecommerce.server.components.database;

import android.content.Context;

import com.beebrainy.heady.ecommerce.server.contracts.CallbackResponse;

/**
 * Business logic for downloading and dumping into database.
 */
public interface IDatabaseDownloader {

    //SharedPreferences key for Database Downloaded flag.
    String KEY_DB_LOADED = "KEY_DB_LOADED";
    //URL for for Json Database
    String JSON_URL = "https://stark-spire-93433.herokuapp.com/json";

    /**
     * Downloads database from network and persists in the database.
     *
     * @param context  - {@link Context}
     * @param response - true for success & false for failure
     */
    void downloadDatabase(Context context, CallbackResponse<Boolean> response);

    /**
     * Clears the database in case of any inconsistency.
     */
    void clearDb();
}
