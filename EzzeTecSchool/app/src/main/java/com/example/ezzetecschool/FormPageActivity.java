package com.example.ezzetecschool;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class FormPageActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_form_page);




    }


       //onclick method create

    public void loginn(View view) {

        Intent intent=new Intent(FormPageActivity.this,loginActivity.class);
        startActivity(intent);

    }

    public void registration(View view) {

        Intent intent=new Intent(FormPageActivity.this,RegistrationActivity.class);
        startActivity(intent);

    }
}
