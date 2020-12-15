package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.modelPublicHeadingGET.Datum;

public class PublicHearingDetailsActivity extends AppCompatActivity {

    Datum dataClass=null;

    TextView nameTV,mobileTV,addressTV,titleTV,descriptionTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_hearing_details);

        Intent i = getIntent();
        dataClass = (Datum) i.getSerializableExtra("data");

        fieldInit();

        nameTV.setText(dataClass.getName());
        mobileTV.setText(dataClass.getMobileNo());
        addressTV.setText(dataClass.getAddress());
        titleTV.setText(dataClass.getSubject());
        descriptionTV.setText(dataClass.getDescription());

    }

    private void fieldInit() {
        nameTV = findViewById(R.id.nameTV);
        mobileTV = findViewById(R.id.mobileTV);
        addressTV = findViewById(R.id.addresTV);
        titleTV = findViewById(R.id.titleTV);
        descriptionTV = findViewById(R.id.descriptionTV);

    }

    public void backBtn(View view) {
        onBackPressed();
    }
}