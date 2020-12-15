package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelPublicHeadingPOST.PublicHearingSaveResponse;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentOfDCActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener{

    private LinearLayout selectedForDateIV,l1;
    private TextView dateTxt,text1;

    EditText subjectET,detailsET, nameET,addressET, mobileNoET;
    String subject,details,name,address,mobileNo;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_of_d_c);

        initField();
        setSpinner2();

        fieldInit();
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

    private void setSpinner2() {
        Spinner spinner = findViewById(R.id.appointmentSubjectSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.appointmentSubject, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(AppointmentOfDCActivity.this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String date = month + "/" + dayOfMonth + "/" + year;
        dateTxt.setText(date);
    }


    //----------ruf-----------------
    private void fieldInit() {
        subjectET = findViewById(R.id.subjectET);
        detailsET = findViewById(R.id.detailsET);
        nameET = findViewById(R.id.nameET);
        addressET = findViewById(R.id.addressET);
        mobileNoET = findViewById(R.id.mobileNoET);
        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
    }

    private void createPublicHearing() {
        subject = subjectET.getText().toString().trim();
        details = detailsET.getText().toString().trim();
        name = nameET.getText().toString().trim();
        address = addressET.getText().toString().trim();
        mobileNo = mobileNoET.getText().toString().trim();

        if (subject.isEmpty()) {
            subjectET.setError("required");
            subjectET.requestFocus();
            return;
        } else if (details.isEmpty()) {
            detailsET.setError("required");
            detailsET.requestFocus();
        } else if (name.isEmpty()) {
            nameET.setError("required");
            nameET.requestFocus();
        }
        else if (address.isEmpty()) {
            addressET.setError("required");
            addressET.requestFocus();
        }
        else if (mobileNo.isEmpty()) {
            mobileNoET.setError("required");
            mobileNoET.requestFocus();
        }
        else {
            final ProgressDialog mDialog = new ProgressDialog(AppointmentOfDCActivity.this);
            mDialog.setMessage("Please waiting...");
            mDialog.show();

         //   int id = Integer.parseInt(Common.USER_ID);
            Call<PublicHearingSaveResponse> call = apiInterface.setPublicHearing("A1b1C2d32564kjhkjadu",
                    name,subject, mobileNo,address,details,1);

            call.enqueue(new Callback<PublicHearingSaveResponse>() {
                @Override
                public void onResponse(Call<PublicHearingSaveResponse> call, Response<PublicHearingSaveResponse> response) {

                    if (response.code() == 200) {
                        PublicHearingSaveResponse meg = response.body();

                        subjectET.setText(null);
                        detailsET.setText(null);
                        nameET.setText(null);
                        mobileNoET.setText(null);
                        addressET.setText(null);
                        // Toast.makeText(SignInActivity.this, ""+userAssessToken, Toast.LENGTH_LONG).show();
                        Toast.makeText(AppointmentOfDCActivity.this, "Congratulations!! Your data saved successfully ", Toast.LENGTH_LONG).show();
                        mDialog.dismiss();


                    } else if (response.code() == 203) {
                        Toast.makeText(AppointmentOfDCActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 401) {
                        Toast.makeText(AppointmentOfDCActivity.this, "Unauthorized Access", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 422) {
                        Toast.makeText(AppointmentOfDCActivity.this, "Validation Error", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<PublicHearingSaveResponse> call, Throwable t) {

                    Toast.makeText(AppointmentOfDCActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            });
        }
    }

    public void btnSubmit(View view) {
        createPublicHearing();
    }

}