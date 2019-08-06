package com.ncu.myapplication_bottomnavigationtest_3.model;


public class Users {
    private String profile_addr;//头像地址
    private String name;//昵称
    private int number;//序号
    private String account;//账号，用来登录和注册的
    private String password;//密码
    private String gender;//性别
    private String account_num;//用户号，相当于微信号

    public String getProfile_addr() {
        return profile_addr;
    }

    public void setProfile_addr(String profile_addr) {
        this.profile_addr = profile_addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccount_num() {
        return account_num;
    }

    public void setAccount_num(String account_num) {
        this.account_num = account_num;
    }
}
