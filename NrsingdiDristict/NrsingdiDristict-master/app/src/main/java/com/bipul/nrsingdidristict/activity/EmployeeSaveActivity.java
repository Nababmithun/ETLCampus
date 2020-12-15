package com.bipul.nrsingdidristict.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bipul.nrsingdidristict.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.Calendar;

public class EmployeeSaveActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private ImageView getImageIV;
    private LinearLayout selectedIV;
    private LinearLayout selectedForDateIV,l1;
    private TextView dateTxt,imageNameTV;
    private EditText nameET,emailET,phoneET,designationET,bcsBatchET;
    private String name,email,phone,designation,bcsBatch;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_save);

        initField();

        selectedForDateIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    private void initField() {
        selectedIV = findViewById(R.id.selectedIV);
        getImageIV = findViewById(R.id.getImageIV);
        selectedForDateIV = findViewById(R.id.selectedForDateIV);
        dateTxt = findViewById(R.id.dateTxt);

        selectedIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pickFile();

                takeImage();
            }
        });
    }

    private void takeImage() {
        ImagePicker.Companion.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Uri uri =  data.getData();
            if (uri != null){
                getImageIV.setImageURI(uri);
                getImageIV.setVisibility(View.VISIBLE);
            }else {
                getImageIV.setVisibility(View.GONE);
            }
        }
    }

    public void backBtn(View view) {
        onBackPressed();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String date = year + "-" + month + "-" + dayOfMonth;
        dateTxt.setText(date);
    }

}