package com.ncu.myapplication_bottomnavigationtest_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gyf.immersionbar.ImmersionBar;
import com.ncu.myapplication_bottomnavigationtest_3.model.Users;

public class Activity_edit extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ImmersionBar.with(this).statusBarDarkFont(true).init();

        LinearLayout user_profile = (LinearLayout) findViewById(R.id.user_profile);
        LinearLayout user_name = (LinearLayout) findViewById(R.id.user_name);
        LinearLayout user_account = (LinearLayout) findViewById(R.id.user_account);


        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_edit.this, Edit_profile.class);
                startActivity(intent);
            }
        });


        user_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_edit.this, Edit_name.class);
                startActivity(intent);
            }
        });


        user_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_edit.this, Edit_account.class);
                startActivity(intent);
            }
        });
        final MyApplication myApplication = (MyApplication) getApplication();


        final Users user = new Users();


        /**************************/
        //Volley存入数据库

        user.setAccount(myApplication.getAccount());

        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = getResources().getString(R.string.getinfo) + "?account=" + user.getAccount();
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            TextView username = (TextView) findViewById(R.id.name);
                            TextView accountnum = (TextView) findViewById(R.id.accountnum);
                            String[] strArr = response.split("\\+");
                            String name = strArr[0];
                            String account_num = strArr[1];
                            user.setName(name);
                            user.setAccount_num(account_num);
                         /*   Toast.makeText(getApplicationContext(), "name:" +
                                    user.getName() + "num: " + user.getAccount_num(), Toast.LENGTH_SHORT).show();

                          */
                            username.setText(user.getName());
                            accountnum.setText(user.getAccount_num());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //网络连接失败
                Toast.makeText(getApplicationContext(), "网络连接失败！", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
        /************************************/
    }
}
