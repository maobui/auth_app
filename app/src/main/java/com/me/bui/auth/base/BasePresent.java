package com.me.bui.auth.base;

/**
 * Created by mao.bui on 6/27/2018.
 */
public class BasePresent<V extends IBaseView> implements IBasePresent<V>{
    protected V mV;

    @Override
    public void attachView(V v) {
        this.mV = v;
    }

    @Override
    public void dettachView() {
        this.mV = null;
    }

    @Override
    public void destroy() {

    }
}
