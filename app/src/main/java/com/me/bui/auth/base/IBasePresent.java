package com.me.bui.auth.base;

/**
 * Created by mao.bui on 6/27/2018.
 */
public interface IBasePresent <V extends IBaseView>{

    void attachView(V view);

    void dettachView();

    void destroy();
}
