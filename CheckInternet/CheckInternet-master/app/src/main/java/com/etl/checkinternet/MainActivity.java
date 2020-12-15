package com.etl.checkinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnNetworkStateChangeListener {

    private int networkStateChangeCount = 0;
    TextView noInternetTV;
    private NetworkChangeReceiver mNetworkReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        noInternetTV = findViewById(R.id.noInternetTV);
        init();

        if (ConnectivityHelper.isConnected(this)) {
            checkNextActivity();
        }

    }

    private void init() {
        mNetworkReceiver = new NetworkChangeReceiver(this);
        registerNetworkBroadcast();
    }

    private void checkNextActivity() {
        startActivity(new Intent(MainActivity.this,NextActivity.class));
    }

    private void registerNetworkBroadcast() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    private void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ConnectivityHelper.isConnected(this) == true) {
            noInternetTV.setVisibility(View.GONE);
        } else {
            noInternetTV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onChange(boolean isConnected) {
        networkStateChangeCount++;
        if (isConnected) {
            noInternetTV.setBackgroundColor(getResources().getColor(R.color.green));
            noInternetTV.setText(getResources().getString(R.string.back_online));

            if (networkStateChangeCount >= 2) {
              //  checkNextActivity();
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    CustomVisibility.collapse(noInternetTV, 500);
                }
            }, 2000);
        } else {
            noInternetTV.setBackgroundColor(getResources().getColor(R.color.red));
            noInternetTV.setText(getResources().getString(R.string.no_internet_connection));
            CustomVisibility.expand(noInternetTV, 500);
        }
    }







}
