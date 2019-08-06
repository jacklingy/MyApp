package com.ncu.myapplication_bottomnavigationtest_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gyf.immersionbar.ImmersionBar;

import java.util.Timer;
import java.util.TimerTask;

public class Activity_login extends AppCompatActivity {
    private BottomNavigationItemView bottomNavigationItemView;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setTitle("登录");

        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        setContentView(R.layout.activity_login);
        final Button button = (Button) findViewById(R.id.btn);


        TextView textView = findViewById(R.id.register);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_login.this, Activity_register.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setClickable(false);
                button.setText("登录中...");
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        button.setClickable(true);
                        button.setText("登录");
                        // button.setBackgroundColor(Color.argb(0,200,200,200));
                    }
                };
                timer.schedule(timerTask, 2000);


                EditText et_account = findViewById(R.id.account);
                EditText et_password = findViewById(R.id.password);

                final String account = et_account.getText().toString();
                String password = et_password.getText().toString();


                final TextView mTextView = (TextView) findViewById(R.id.text);
// ...


// Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                //below is the ip address of my server at the current LAN.
                //You'd better change it into your own ip address
                String url = getResources().getString(R.string.url) + "/login.php?account=" + account + "&password=" + password;

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.contains("1")) {
                                    //登录成功！
                                    MyApplication myApplication = (MyApplication) getApplication();
                                    myApplication.setLogin_state(1);
                                    myApplication.setAccount(account);
                                    Toast.makeText(getApplicationContext(), "登录成功！loginState为: " +
                                            myApplication.getLogin_state(), Toast.LENGTH_SHORT).show();
                                    finish();
                                    try {
                                        wait(200);
                                        finish();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    //跳转到下一个页面
                                    /********更改登录状态********/
//                                    MyApplication myApplication = (MyApplication) getApplication();
//                                    myApplication.setLogin_state(1);

//                                    LoginState loginState;
//                                    loginState.setState(1);
                                    //此处不用跳转
                                    // Toast.makeText(getApplicationContext(), myApplication.getLogin_state(), Toast.LENGTH_SHORT).show();

                                    //finish();
                                    //Intent intent = new Intent(Activity_login.this, MainActivity.class);
                                    //startActivity(intent);

                                }
                                if (response.contains("0")) {
                                    //登录失败！
                                    Toast.makeText(getApplicationContext(), "登录失败，请重试！", Toast.LENGTH_SHORT).show();

                                }
                                // Display the first 500 characters of the response string.
                                //  mTextView.setText("Response is: "+ response.substring(0,8));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //网络连接失败
                        mTextView.setText("网络连接失败！");
                        error.printStackTrace();
                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });

    }//onCreate
}
