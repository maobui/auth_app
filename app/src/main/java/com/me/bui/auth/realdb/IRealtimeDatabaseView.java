package com.me.bui.auth.realdb;

import com.me.bui.auth.base.IBaseView;
import com.me.bui.auth.realdb.model.User;

/**
 * Created by mao.bui on 6/28/2018.
 */
public interface IRealtimeDatabaseView extends IBaseView {
    void updateActionBarTitle(String title);
    void updateUI(User user);
}
