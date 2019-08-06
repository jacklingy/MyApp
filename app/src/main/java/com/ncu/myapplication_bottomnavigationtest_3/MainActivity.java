package com.ncu.myapplication_bottomnavigationtest_3;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private LinearLayout layout_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("appSetting", MODE_PRIVATE);
        //   SharedPreferences.Editor editor = sharedPreferences.edit();

        //  String mode=sharedPreferences.getString("mode","");

        int switch_stat = sharedPreferences.getInt("switch", 0);
        Toast.makeText(this, "the value of switch is : " + switch_stat, Toast.LENGTH_LONG).show();

        if (switch_stat == 0) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            // recreate();
        }
        if (switch_stat == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            //recreate();
        }


        //sharedPreferences.getBoolean("IsFirstView", false);
//        if (switch_stat==0)
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        recreate();
//        if(switch_stat==1)
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        recreate();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //透明化StatusBar & 取消ActionBar
        ImmersionBar.with(this).init();


//        if (mode=="MODE_NIGHT_YES")
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        else
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // this.recreate();


//        SharedPreferences sharedPreferences = getSharedPreferences("appSetting", MODE_PRIVATE);
//        int switch_stat = sharedPreferences.getInt("switch", 0);
//
//        if (switch_stat == 1) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            recreate();
//        } else if (switch_stat == 0) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            recreate();
//        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        Inflater inflater = new Inflater();
        // layout_edit=


        //进入activity时选择显示第一个fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment())
                .addToBackStack(null)
                .commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new LikesFragment();
                            break;
                        case R.id.nav_my:
                            selectedFragment = new MyFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

}
