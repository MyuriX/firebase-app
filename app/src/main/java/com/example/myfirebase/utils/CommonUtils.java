package com.example.myfirebase.utils;

import android.app.ProgressDialog;

import com.example.myfirebase.R;

public class CommonUtils {
    public static void showProgressDialog(String message, ProgressDialog progress) {

        if (!progress.isShowing()) {
            progress.setMessage(message);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();
        } else {
            progress.cancel();

        }

    }
}
