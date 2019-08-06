package com.ncu.myapplication_bottomnavigationtest_3;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.gyf.immersionbar.ImmersionBar;

public class Activity_setting extends AppCompatActivity {
    private AlertDialog.Builder builder;


    private void showDialog() {

        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("退出登录")
                .setMessage("确定要退出登录吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        //  Toast.makeText(, "确定按钮", Toast.LENGTH_LONG).show();
                        finish();

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        //  Toast.makeText(MainActivity.this, "关闭按钮", Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /****************退出登录的可见性******************/

        //   LinearLayout logout=(LinearLayout)findViewById(R.id.loginOut);


        setContentView(R.layout.activity_setting);
        ActionBar actionBar = getSupportActionBar();
        ImmersionBar.with(this).statusBarDarkFont(true).init();


        // actionBar.setTitle("设置");

        LinearLayout linearLayout_loginOut = (LinearLayout) findViewById(R.id.loginOut);

        MyApplication myApplication = (MyApplication) getApplication();
        if (myApplication.getLogin_state() == 0)
            linearLayout_loginOut.setVisibility(View.GONE);
        linearLayout_loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication myApplication = (MyApplication) getApplication();
                myApplication.setLogin_state(0);
                showDialog();


//                Intent intent=new Intent(Activity_setting.this,MainActivity.class);
//                startActivity(intent);
            }
        });
        Switch aSwitch = (Switch) findViewById(R.id.aswitch);

        SharedPreferences sharedPreferences = getSharedPreferences("appSetting", MODE_PRIVATE);
        int switch_stat = sharedPreferences.getInt("switch", 0);//存开关状态
        if (switch_stat == 1)
            aSwitch.setChecked(true);
        if (switch_stat == 0)
            aSwitch.setChecked(false);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    //finish();
                    //startActivity(new Intent(,this.getClass()));
                    overridePendingTransition(0, 0);
                    SharedPreferences sharedPreferences = getSharedPreferences("appSetting", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //editor.putString("mode", "MODE_NIGHT_YES");
                    editor.putInt("switch", 1);//开是1，关是0
                    editor.commit();
                    //刷新
                    recreate();

                    // recreate();
                } else {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    /****/
                    // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences sharedPreferences = getSharedPreferences("appSetting", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //editor.putString("mode", "MODE_NIGHT_NO");
                    editor.putInt("switch", 0);//开是1，关是0

                    editor.commit();

                    //刷新

                    recreate();
/***Activity.recreate();*/
                    //  recreate();

                }
            }
        });
    }
}
