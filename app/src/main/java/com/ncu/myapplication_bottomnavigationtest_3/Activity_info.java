package com.ncu.myapplication_bottomnavigationtest_3;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gyf.immersionbar.ImmersionBar;

public class Activity_info extends AppCompatActivity {
    private AlertDialog.Builder builder;

    private void showDialog() {

        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("检查更新")
                .setMessage("检查到新版本，确定要更新吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        //  Toast.makeText(, "确定按钮", Toast.LENGTH_LONG).show();
                        Toast.makeText(Activity_info.this, "开始下载更新...", Toast.LENGTH_SHORT);
                        String url = getResources().getString(R.string.updateurl);
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                        request.setDescription("正在下载更新...");
                        request.setTitle("最新版本");
// in order for this if to run, you must use the android 3.2 to compile your app
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        }
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "latest.apk");
// get download service and enqueue file
                        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        manager.enqueue(request);

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
        if (ContextCompat.checkSelfPermission(Activity_info.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Activity_info.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ActionBar actionBar = getSupportActionBar();
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        // actionBar.setTitle("关于");


        LinearLayout check = (LinearLayout) findViewById(R.id.checkupdate);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //检查更新
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = getResources().getString(R.string.url) + "/checkupdate";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("1"))
                            //有新版本
                            showDialog();
                        else {
                            Toast.makeText(getApplicationContext(), "已经是最新版本!", Toast.LENGTH_LONG).show();
                            //已经是最新版本
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //网络连接失败
                        Toast.makeText(getApplicationContext(), "检查更新失败！", Toast.LENGTH_LONG).show();
                        // mTextView.setText("网络连接失败！");
                        error.printStackTrace();
                    }
                });
                queue.add(stringRequest);


            }
        });

    }
}
