package com.ncu.myapplication_bottomnavigationtest_3.model;

public class LoginState {
    private int state = 0;
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
