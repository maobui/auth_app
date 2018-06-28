package com.me.bui.auth.auth.signup;

import android.app.Activity;

import com.me.bui.auth.base.IBaseView;

/**
 * Created by mao.bui on 6/27/2018.
 */
public interface ISignupView  extends IBaseView {
    Activity getActivityClass();
    void showWarningEmail();
    void showWarningPassword();
    void showWarningPasswordMin();
    void showErrorCreateFailed();
    void showSuccessCreated();
}
