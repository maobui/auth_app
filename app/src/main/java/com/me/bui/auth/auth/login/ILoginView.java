package com.me.bui.auth.auth.login;

import android.app.Activity;
import android.view.View;

import com.me.bui.auth.base.IBaseView;

/**
 * Created by mao.bui on 6/28/2018.
 */
public interface ILoginView extends IBaseView{
    Activity getActivityClass();
    void showWarningEmail();
    void showWarningPassword();
    void setInputPasswordError();
    void showAuthFailed();
}
