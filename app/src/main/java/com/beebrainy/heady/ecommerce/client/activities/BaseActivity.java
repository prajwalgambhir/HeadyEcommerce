package com.beebrainy.heady.ecommerce.client.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Prajwal Gambhir on 14-Jun-18.
 * Copyright (2018) by Cogitate Technology Solution
 */

public class BaseActivity extends AppCompatActivity {

    protected ProgressDialog getPDialog(String title, String message, boolean cancellable) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(cancellable);
        return dialog;
    }

    protected void showAlert(String title, String message, String btnText) {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        AlertDialog dialog = aBuilder.create();
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setButton(btnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
