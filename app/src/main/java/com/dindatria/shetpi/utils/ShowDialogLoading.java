package com.dindatria.shetpi.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

import com.dindatria.shetpi.R;

public class ShowDialogLoading {
    private Activity activity;
    private AlertDialog dialog;

    public ShowDialogLoading(Activity activity) {
        this.activity = activity;
    }


    public void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.item_loading, null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismissDialog() {
        dialog.dismiss();
    }
}
