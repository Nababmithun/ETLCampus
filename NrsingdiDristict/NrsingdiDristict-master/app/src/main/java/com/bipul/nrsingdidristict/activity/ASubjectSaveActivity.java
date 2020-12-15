package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.common.Common;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelForAppoinmentSubjectSavePOST.AppointmentSubjectSaveResponse;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ASubjectSaveActivity extends AppCompatActivity {

    private TextInputEditText appointmentSubjectET;
    private String appointmentSubject;
    private Button btnSubmit;

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_subject_save);


        appointmentSubjectET = findViewById(R.id.appointmentSubjectSaveET);
        btnSubmit = findViewById(R.id.btnSubmit);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSubjectSave();
            }
        });

    }

    private void createSubjectSave() {
        appointmentSubject = appointmentSubjectET.getText().toString().trim();
        if (appointmentSubject.isEmpty()) {
            appointmentSubjectET.setError("required");
            appointmentSubjectET.requestFocus();
        } else {
            final ProgressDialog mDialog = new ProgressDialog(ASubjectSaveActivity.this);
            mDialog.setMessage("Please waiting...");
            mDialog.show();

            apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);

            Call<AppointmentSubjectSaveResponse> call = apiInterface.setAppointmentSubjectSaveResponse(Common.APP_KEY, "1", appointmentSubject);

            call.enqueue(new Callback<AppointmentSubjectSaveResponse>() {
                @Override
                public void onResponse(Call<AppointmentSubjectSaveResponse> call, Response<AppointmentSubjectSaveResponse> response) {
                    if (response.code() == 200) {
                        AppointmentSubjectSaveResponse serverResponse = response.body();
                        Toast.makeText(ASubjectSaveActivity.this, "" + serverResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        mDialog.dismiss();
                    } else if (response.code() == 203) {
                        Toast.makeText(ASubjectSaveActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 401) {
                        Toast.makeText(ASubjectSaveActivity.this, "Unauthorized Access", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 422) {
                        Toast.makeText(ASubjectSaveActivity.this, "Validation Error", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<AppointmentSubjectSaveResponse> call, Throwable t) {

                    mDialog.dismiss();
                }
            });
        }


    }

    public void backBtn(View view) {
        onBackPressed();
    }
}