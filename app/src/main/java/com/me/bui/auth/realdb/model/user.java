package com.me.bui.auth.realdb.model;

import com.google.firebase.database.IgnoreExtraProperties;
/**
 * Created by mao.bui on 6/28/2018.
 */
@IgnoreExtraProperties
public class User {

    public String name;
    public String email;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}