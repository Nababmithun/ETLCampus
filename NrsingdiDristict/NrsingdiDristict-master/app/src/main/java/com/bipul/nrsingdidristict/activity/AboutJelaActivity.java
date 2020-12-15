package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bipul.nrsingdidristict.R;

public class AboutJelaActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_jela);
    }

    public void backBtn(View view) {
        onBackPressed();
    }
}