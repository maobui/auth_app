package com.me.bui.auth.auth.login;

import android.app.Activity;

import com.me.bui.auth.base.IBaseView;

/**
 * Created by mao.bui on 6/28/2018.
 */
public interface ILoginView extends IBaseView{
    Activity getActivityClass();
    void setInputPasswordError(CharSequence error);
}
