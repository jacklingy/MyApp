package com.ncu.myapplication_bottomnavigationtest_3;

import android.app.Application;

public class MyApplication extends Application {
    private int login_state = 0;//0表示没有登录；1表示登录状态；

    public int getLogin_state() {
        return login_state;
    }

    public void setLogin_state(int login_state) {
        this.login_state = login_state;
    }


    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}

