package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.modelForEmployeeGET.Datum;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    CircleImageView stuffImageIV;
    TextView nameTV,phoneNumberTV,designationTV,emailTV,bcsBatchTV,dateTV;

    Datum employee = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initField();

        Intent i = getIntent();
        employee = (Datum) i.getSerializableExtra("employee");

        Picasso.get().load("http://apis.digiins.gov.bd/district_app/public/employee/"+employee.getPicture())
                .into(stuffImageIV);
       nameTV.setText(employee.getName());
       phoneNumberTV.setText(employee.getMobileNo());
        designationTV.setText(employee.getDesignation());
        emailTV.setText(employee.getEmail());
        bcsBatchTV.setText(employee.getBcsBatch());
        dateTV.setText(employee.getJoiningDate());

    }

    private void initField() {
        stuffImageIV = findViewById(R.id.stuffImageIV);
        nameTV = findViewById(R.id.profile_nameTV);
        phoneNumberTV = findViewById(R.id.phoneNumberTV);
        designationTV = findViewById(R.id.designationTV);
        emailTV = findViewById(R.id.emailTV);
        bcsBatchTV = findViewById(R.id.bcsbatchTV);
        dateTV = findViewById(R.id.dateTV);
    }

    public void backBtn(View view) {
        onBackPressed();
    }

    public void clickWhatsapp(View view) {
       /* Uri uri = Uri.parse("01304568660");
        Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
        intent.setPackage("com.whatsapp");
        startActivity(intent);*/

       /* Intent sendIntent = new Intent(); sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);*/

       openTab("https://chat.whatsapp.com/JNZT1EYn6WYBZJJ9J0UITM");

    }

    public void openTab(String url){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(ProfileActivity.this, Uri.parse(url));
    }

}