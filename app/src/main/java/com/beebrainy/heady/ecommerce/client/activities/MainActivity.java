package com.beebrainy.heady.ecommerce.client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.beebrainy.heady.ecommerce.R;
import com.beebrainy.heady.ecommerce.client.service.StorageService;
import com.beebrainy.heady.ecommerce.server.components.database.IDatabaseDownloader;
import com.beebrainy.heady.ecommerce.server.components.database.di.DBDownloaderComponent;
import com.beebrainy.heady.ecommerce.server.components.database.di.DaggerDBDownloaderComponent;

import javax.inject.Inject;

import static com.beebrainy.heady.ecommerce.server.components.database.IDatabaseDownloader
        .DB_LOADED;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCat, btnRanking;
    private DBDownloaderComponent component;
    @Inject
    IDatabaseDownloader iDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component = DaggerDBDownloaderComponent.builder().build();
        component.inject(this);
        initView();
        initDB();
    }

    private void initView() {
        btnCat = findViewById(R.id.btnCat);
        btnCat.setOnClickListener(this);
        btnRanking = findViewById(R.id.btnRanking);
        btnRanking.setOnClickListener(this);
    }

    private void initDB() {
        StorageService storageService = new StorageService(this);
        if (!storageService.getBoolean(DB_LOADED) || true) {
            iDb.clearDb(this);
            Log.d(MainActivity.class.getSimpleName(), "Downloading DB");
            iDb.downloadDatabase(this);
            Log.d(MainActivity.class.getSimpleName(), "DB Downloading Finished");
        } else {
            Log.d(MainActivity.class.getSimpleName(), "DB Already exists. Do Nothing.");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCat: {
                startActivity(new Intent(this, CategoryListActivity.class));
                break;
            }
            case R.id.btnRanking: {
                startActivity(new Intent(this, RankingActivity.class));
                break;
            }
        }
    }
}
