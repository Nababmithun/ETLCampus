package com.bipul.nrsingdidristict.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.common.Common;
import com.bipul.nrsingdidristict.common.RangeTimePickerDialog;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelForComplainSavePOST.ComplainSaveResponse;
import com.bipul.nrsingdidristict.modelForWorkSchedulePOST.WorkScheduleSaveResponse;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker;

public class WorkScheduleActivity extends AppCompatActivity {

    LinearLayout dateLL, timeLL;
    private TextView dateTV;
    private ApiInterface apiService;
    private EditText workSubjectET, workPlaceET, workDetailsET;
    private String workSubject, workPlace, workDetails, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_schedule);

        initField();

        dateLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePicker();
            }
        });

    }

    private void initField() {
        dateLL = findViewById(R.id.dateLL);
        dateTV = findViewById(R.id.dateTV);
        workSubjectET = findViewById(R.id.workSubjectET);
        workPlaceET = findViewById(R.id.workPlaceET);
        workDetailsET = findViewById(R.id.workDetailsET);


    }

    public void backBtn(View view) {
        onBackPressed();
    }


    private void datePicker() {
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        int year = calendar.get(java.util.Calendar.YEAR);
        int month = calendar.get(java.util.Calendar.MONTH);
        int dayOfMonth = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                //String date = getResources().getString(R.string.date_format);
                java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
                String dateS = simpleDateFormat.format(calendar.getTime());
                @SuppressLint({"StringFormatInvalid", "LocalSuppress"}) String formatDate = String.format(dateS, year, month + 1, dayOfMonth);
                dateTV.setText(formatDate);
                timePicker(formatDate);
            }
        }, year, month, dayOfMonth);
        calendar.add(java.util.Calendar.MONTH, 1);
        long now = System.currentTimeMillis() - 1000;
        long maxDate = calendar.getTimeInMillis();
        datePickerDialog.getDatePicker().setMinDate(now);
        datePickerDialog.getDatePicker().setMaxDate(maxDate); //After one month from now
        datePickerDialog.show();
    }

    private void timePicker(final String date) {
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        final int hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(java.util.Calendar.MINUTE);

        RangeTimePickerDialog timePickerDialog = new RangeTimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // String time = getResources().getString(R.string.time_format);
                java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("hh:mm a");
                String times = simpleDateFormat.format(calendar.getTime());
                @SuppressLint({"StringFormatInvalid", "LocalSuppress"}) String formatTime = String.format(times, hourOfDay, minute);
                String dateTime = date + "   " + formatTime;
                dateTV.setText(dateTime);
            }
        }, hour + 1, minute, false);
        timePickerDialog.setMin(hour + 1, minute);
        timePickerDialog.show();
    }

    public void btnSubmit(View view) {
        createWorkSchedule();
    }

    private void createWorkSchedule() {

        workSubject = workSubjectET.getText().toString().trim();
        workDetails = workDetailsET.getText().toString().trim();
        workPlace = workPlaceET.getText().toString().trim();
        date = dateTV.getText().toString().trim();

        if (workSubject.isEmpty()) {
            workSubjectET.setError("required");
            workSubjectET.requestFocus();

        } else if (workDetails.isEmpty()) {
            workDetailsET.setError("required");
            workDetailsET.requestFocus();
        } else if (workPlace.isEmpty()) {
            workPlaceET.setError("required");
            workPlaceET.requestFocus();
        } else if (date.isEmpty()) {
            dateTV.setError("required");
            dateTV.requestFocus();
        } else {
            apiService = RetrofitClient.getRetrofit().create(ApiInterface.class);

            final ProgressDialog mDialog = new ProgressDialog(WorkScheduleActivity.this);
            mDialog.setMessage("Please waiting...");
            mDialog.show();

            Call<WorkScheduleSaveResponse> call = apiService.setWorkScheduleSaveResponse(Common.APP_KEY, 1, 212121, workSubject, date, workPlace, workDetails);

            call.enqueue(new Callback<WorkScheduleSaveResponse>() {
                @Override
                public void onResponse(Call<WorkScheduleSaveResponse> call, Response<WorkScheduleSaveResponse> response) {
                    if (response.code() == 200) {
                        WorkScheduleSaveResponse meg = response.body();


                        // Toast.makeText(SignInActivity.this, ""+userAssessToken, Toast.LENGTH_LONG).show();
                        Toast.makeText(WorkScheduleActivity.this, ""+meg.getMessage(), Toast.LENGTH_LONG).show();
                        mDialog.dismiss();

                        workDetailsET.setText("");
                        workPlaceET.setText("");
                        workSubjectET.setText("");
                        dateTV.setText("");



                    } else if (response.code() == 203) {
                        Toast.makeText(WorkScheduleActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 401) {
                        Toast.makeText(WorkScheduleActivity.this, "Unauthorized Access", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 422) {
                        Toast.makeText(WorkScheduleActivity.this, "Validation Error", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<WorkScheduleSaveResponse> call, Throwable t) {

                }
            });
        }

    }
}