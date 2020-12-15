package com.example.ezzetecschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoteActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);





    }

    public void chodna(View view) {

        Intent dhon=new Intent(NoteActivity.this,MainActivity.class);
        startActivity(dhon);
    }
}
