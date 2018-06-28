package com.me.bui.auth.base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by mao.bui on 6/27/2018.
 */
public interface IBaseView {
    void setUp();
    void startActivity (Class<? extends Activity> clazz);
    void startActivityAndFinish(Class<? extends Activity> clazz);

    void showLoading();
    void hideLoading();
    void showWarning(@StringRes int resId);
    void showError(@StringRes int resId);
    void showSuccess(@StringRes int resId);
}
