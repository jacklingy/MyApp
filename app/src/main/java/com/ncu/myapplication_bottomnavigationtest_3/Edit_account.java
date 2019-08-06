package com.ncu.myapplication_bottomnavigationtest_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gyf.immersionbar.ImmersionBar;
import com.ncu.myapplication_bottomnavigationtest_3.model.Users;

public class Edit_account extends AppCompatActivity {
    private String account_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarDarkFont(true).statusBarDarkFont(true).init();
        setContentView(R.layout.activity_edit_account);
        Button edit_button = findViewById(R.id.edit_button);


        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit_name = findViewById(R.id.edit_name);
                account_num = edit_name.getText().toString();
                //Toast.makeText(Edit_name.this,"刚刚输入的名字是："+savedName,Toast.LENGTH_SHORT).show();

                Users user = new Users();
                user.setAccount_num(account_num);
                MyApplication myApplication = (MyApplication) getApplication();
                user.setAccount(myApplication.getAccount());
                Toast.makeText(Edit_account.this, "要更改的用户名是：" + user.getAccount() +
                        "改为：" + user.getAccount_num(), Toast.LENGTH_LONG).show();

                //Volley存入数据库
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = getResources().getString(R.string.updateaccountnum) + "?account=" + user.getAccount()
                        + "&accountnum=" + user.getAccount_num();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.contains("1")) {
                                    //修改成功
                                    Toast.makeText(getApplicationContext(), "修改昵称成功！ ",
                                            Toast.LENGTH_SHORT).show();
                                    // finish();
                                    try {
                                        wait(200);
                                        // finish();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (response.contains("0")) {
                                    //修改失败！
                                    Toast.makeText(getApplicationContext(), "修改昵称失败，请重试！", Toast.LENGTH_SHORT).show();
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
        });
    }
}
