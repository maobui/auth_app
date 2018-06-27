package com.me.bui.auth.base;

import android.app.Activity;
import android.content.Context;

/**
 * Created by mao.bui on 6/27/2018.
 */
public interface IBaseView {

    Context getContext();
    void startActivity (Class<? extends Activity> clazz);
    void startActivityAndFinish(Class<? extends Activity> clazz);

    void showLoading();
    void hideLoading();
}
