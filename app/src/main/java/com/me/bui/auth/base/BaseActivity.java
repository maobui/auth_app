package com.me.bui.auth.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mao.bui on 6/27/2018.
 */
public class BaseActivity extends AppCompatActivity implements IBaseView {

    @Override
    public void startActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    public void startActivityAndFinish(Class<? extends Activity> clazz) {
        startActivity(clazz);
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private void showSnackbar(String message, int color) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView tvLabel = sbView.findViewById(android.support.design.R.id.snackbar_text);
        tvLabel.setTextColor(color);
        snackbar.show();
    }

    @Override
    public void showError(String error) {
        this.showSnackbar(error, Color.RED);
    }

    @Override
    public void showWarning(String warning) {
        this.showSnackbar(warning, Color.YELLOW);
    }

    @Override
    public void showSuccess(String success) {
        this.showSnackbar(success, Color.GREEN);
    }
}
