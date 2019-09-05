package com.ncu.myapplication_bottomnavigationtest_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MyFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImmersionBar.with(this).statusBarDarkFont(true).init();


        new Thread() {
            @Override
            public void run() {
                //这里写入子线程需要做的工作


            }
        }.start();


        /*****************************************************/
        LinearLayout layout_edit = getView().findViewById(R.id.func_edit);
        layout_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getActivity(),"++++++-----+++",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), Activity_edit.class);
                startActivity(intent);
            }
        });
/**************************************************************************************/


        LinearLayout layout_info = getView().findViewById(R.id.func_info);
        layout_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getActivity(),"++++++-----+++",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), Activity_info.class);
                startActivity(intent);
            }
        });
/**************************************************************************************/
        LinearLayout layout_set = getView().findViewById(R.id.func_set);
        layout_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getActivity(),"++++++-----+++",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), Activity_setting.class);
                startActivity(intent);
            }
        });
/**************************************************************************************/


        final LinearLayout layout_login_pane = getView().findViewById(R.id.login_pane);
        final LinearLayout layout_signed_pane = getView().findViewById(R.id.signed_pane);

        layout_login_pane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Toast.makeText(getActivity(), "Login pane clicked!", Toast.LENGTH_LONG).show();
//                layout_login_pane.setVisibility(View.GONE);
//                layout_signed_pane.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getActivity(), Activity_login.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onResume() {
        //根据application中存入的登录account,
        // 显示用户名name和用户号account_num

        // MyApplication myApplication=(MyApplication)getActivity().getApplication();


        LinearLayout layout_login_pane = (LinearLayout) getView().findViewById(R.id.login_pane);
        LinearLayout layout_signed_pane = (LinearLayout) getView().findViewById(R.id.signed_pane);

        final TextView username = (TextView) getView().findViewById(R.id.username);
        final TextView accountnum = (TextView) getView().findViewById(R.id.accountnum);
        super.onResume();
        MyApplication myApplication = (MyApplication) getActivity().getApplication();


        if (myApplication.getLogin_state() == 1) {

            layout_login_pane.setVisibility(View.GONE);
            layout_signed_pane.setVisibility(View.VISIBLE);
        //    Toast.makeText(getActivity(), "登录状态为11111111111", Toast.LENGTH_LONG).show();

        } else {
            layout_login_pane.setVisibility(View.VISIBLE);
            layout_signed_pane.setVisibility(View.GONE);
         //   Toast.makeText(getActivity(), "登录状态为00000000000", Toast.LENGTH_LONG).show();

        }
        final Users user = new Users();
        user.setAccount(myApplication.getAccount());
        // String name,account_num;


        /**************************/
        //Volley存入数据库
        final RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = getResources().getString(R.string.getinfo) + "?account=" + user.getAccount();
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String[] strArr = response.split("\\+");
                            String name = strArr[0];
                            String account_num = strArr[1];
                            user.setName(name);
                            user.setAccount_num(account_num);
                          //  Toast.makeText(getActivity().getApplicationContext(), "name:" +
                           //         user.getName() + "num: " + user.getAccount_num(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity().getApplicationContext(), "网络连接失败！", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
        /************************************/


    }
}
