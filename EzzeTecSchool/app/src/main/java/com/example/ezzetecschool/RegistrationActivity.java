package com.example.ezzetecschool;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_registration);
    }

    public void loginnnn(View view) {


        Intent intent=new Intent(RegistrationActivity.this,loginActivity.class);
        startActivity(intent);
    }

    public void login2(View view) {

        Intent intent=new Intent(RegistrationActivity.this,loginActivity.class);
        startActivity(intent);

    }
}
