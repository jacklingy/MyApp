package com.ncu.myapplication_bottomnavigationtest_3;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class Activity_userInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ActionBar actionBar = getSupportActionBar();
        //     actionBar.setTitle("个人信息");

    }
}
