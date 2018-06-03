package com.beebrainy.heady.ecommerce.server.components.database;

import android.content.Context;

public interface IDatabase {

    String DB_LOADED = "DB_LOADED";
    String JSON_URL = "https://stark-spire-93433.herokuapp.com/json";

    void clearDb(Context context);

    void downloadDatabase(Context context);
}
