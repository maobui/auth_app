package com.me.bui.auth.main;

import com.me.bui.auth.base.IBaseView;

/**
 * Created by mao.bui on 6/27/2018.
 */
public interface IMainView extends IBaseView{
    void startLogin();
    String getOldEmail();
    void setOldEmailError(CharSequence error);
    String getNewPassword();
    void setPasswordError(CharSequence error);
    String getNewEmail();
    void setNewEmailError(CharSequence error);

    void showSuccessEmailUpdated();
    void showErrorEmailUpdate();
    void showSuccessPasswordUpdated();
    void showErrorPasswordUpdate();
    void showSuccessPasswordSent();
    void showErrorPasswordSend();
    void showSuccessDeleteUser();
    void showErrorDeleteUserFail();
}
