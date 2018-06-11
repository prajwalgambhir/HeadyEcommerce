package com.beebrainy.heady.ecommerce.server.components.database;

import android.content.Context;

public interface IDatabaseDownloader {

    String DB_LOADED = "DB_LOADED";
    String JSON_URL = "https://stark-spire-93433.herokuapp.com/json";

    void downloadDatabase(Context context);

    void clearDb(Context context);
}
