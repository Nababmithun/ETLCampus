package com.bipul.nrsingdidristict.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.bipul.nrsingdidristict.R;

public class SplashActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        colorChangeStatusBar();

    }

    public void colorChangeStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                boolean login = sharedpreferences.getBoolean("login",false);
//                if (login) {
//                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//                    finish();
//                }else {
//                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//                    finish();
//                }

                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();

            }
        }, 2000);

    }


}