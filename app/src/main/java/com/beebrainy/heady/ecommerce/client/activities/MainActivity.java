package com.beebrainy.heady.ecommerce.client.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.beebrainy.heady.ecommerce.R;
import com.beebrainy.heady.ecommerce.client.service.StorageService;
import com.beebrainy.heady.ecommerce.server.components.database.DatabaseBO;
import com.beebrainy.heady.ecommerce.server.components.database.IDatabase;

import static com.beebrainy.heady.ecommerce.server.components.database.IDatabase.DB_LOADED;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDB();
    }

    private void initDB() {
        StorageService storageService = new StorageService(this);
        if (!storageService.getBoolean(DB_LOADED)) {
            IDatabase iDb = new DatabaseBO();
            iDb.clearDb(this);
            Log.d(MainActivity.class.getSimpleName(), "Downloading DB");
            iDb.downloadDatabase(this);
            Log.d(MainActivity.class.getSimpleName(), "DB Downloading Finished");
        } else {
            Log.d(MainActivity.class.getSimpleName(), "DB Already exists. Do Nothing.");
        }
    }
}
