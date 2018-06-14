package com.beebrainy.heady.ecommerce.client.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.beebrainy.heady.ecommerce.R;
import com.beebrainy.heady.ecommerce.client.activities.di.DaggerMAComponent;
import com.beebrainy.heady.ecommerce.client.activities.di.MAComponent;
import com.beebrainy.heady.ecommerce.client.service.StorageService;
import com.beebrainy.heady.ecommerce.server.components.database.IDatabaseDownloader;
import com.beebrainy.heady.ecommerce.server.contracts.CallbackResponse;

import javax.inject.Inject;

import static com.beebrainy.heady.ecommerce.server.components.database.IDatabaseDownloader
        .KEY_DB_LOADED;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btnCat, btnRanking;
    private MAComponent component;
    @Inject
    IDatabaseDownloader iDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component = DaggerMAComponent.builder().build();
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
        if (!storageService.getBoolean(KEY_DB_LOADED)) {
            iDb.clearDb();
            Log.d(MainActivity.class.getSimpleName(), "Downloading DB");
            final ProgressDialog progressDialog = super.getPDialog("Loading", "Downloading DB. "
                    + "Please wait...", false);
            progressDialog.show();
            iDb.downloadDatabase(this, new CallbackResponse<Boolean>() {
                @Override
                public void onResponse(Boolean aBoolean) {
                    progressDialog.dismiss();
                    Log.d(MainActivity.class.getSimpleName(), "DB Downloading Finished");
                    if (aBoolean) {
                        showAlert("Complete", "DB downloading complete.", "OK");
                    } else {
                        showAlert("Error", "Failed to download DB. Please try again later.", "OK");
                    }
                }
            });
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

    @Override
    protected void showAlert(final String title, final String message, final String btnText) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                MainActivity.super.showAlert(title, message, btnText);
            }
        });
    }
}
