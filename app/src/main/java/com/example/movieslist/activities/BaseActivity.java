package com.example.movieslist.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.movieslist.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;



public class BaseActivity extends AppCompatActivity{
    public static ProgressDialog progressDialog;
    public static final long MAX_CLICK_INTERVAL = 1000;
    protected long lastClickedTime = 0;
    public DisplayMetrics metrices;
    private static BottomSheetDialog dialog = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        metrices = getResources().getDisplayMetrics();
    }

    public void showProgressDialog(Context context) {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                dismissProgressDialog();
            }
            progressDialog = new ProgressDialog(context, R.style.MyGravity);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setCancelable(false);
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }



}
