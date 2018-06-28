package com.me.bui.auth.main;

import com.me.bui.auth.base.IBaseView;

/**
 * Created by mao.bui on 6/27/2018.
 */
public interface IMainView extends IBaseView{
    void startLogin();
    String getOldEmail();
    void setOldEmailError();
    String getNewPassword();
    void setPasswordError();
    void setPasswordErrorNull();
    String getNewEmail();
    void setNewEmailError();

    void showSuccessEmailUpdated();
    void showErrorEmailUpdate();
    void showSuccessPasswordUpdated();
    void showErrorPasswordUpdate();
    void showSuccessPasswordSent();
    void showErrorPasswordSend();
    void showSuccessDeleteUser();
    void showErrorDeleteUserFail();
}
