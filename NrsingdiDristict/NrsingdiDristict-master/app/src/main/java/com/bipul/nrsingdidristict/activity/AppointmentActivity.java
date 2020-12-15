package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bipul.nrsingdidristict.R;

import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener{

    private LinearLayout selectedForDateIV,l1;
    private TextView dateTxt,text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        initField();

        setSpinner1();
        setSpinner2();
    }

    private void initField() {
        selectedForDateIV = findViewById(R.id.selectedForDateIV);
        dateTxt = findViewById(R.id.dateTxt);
        text1 = findViewById(R.id.text1);
        l1 = findViewById(R.id.l1);

        selectedForDateIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }


    private void setSpinner1() {
        Spinner spinner = findViewById(R.id.ComplainTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(AppointmentActivity.this);
    }

    private void setSpinner2() {
        Spinner spinner = findViewById(R.id.personTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.person_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(AppointmentActivity.this);
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

    public void backBtn(View view) {
        onBackPressed();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String date = month + "/" + dayOfMonth + "/" + year;
        dateTxt.setText(date);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if (text.equals("জেলা প্রশাসক")){
            text1.setVisibility(View.GONE);
            l1.setVisibility(View.GONE);
        }else {
            text1.setVisibility(View.VISIBLE);
            l1.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void btnDC(View view) {

        startActivity(new Intent(AppointmentActivity.this,AppointmentOfDCActivity.class));

    }

    public void btnOtherStuff(View view) {
        startActivity(new Intent(AppointmentActivity.this,AppinmentOfOtherStuffActivity.class));

    }
}