package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.common.Common;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelPublicHeadingPOST.PublicHearingSaveResponse;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicHeadingSaveActivity extends AppCompatActivity {

    EditText subjectET,detailsET, nameET,addressET, mobileNoET;
    String subject,details,name,address,mobileNo;

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonosunani);

        fieldInit();
        //Toast.makeText(this, ""+Common.USER_ID, Toast.LENGTH_SHORT).show();
    }

    private void fieldInit() {
        subjectET = findViewById(R.id.subjectET);
        detailsET = findViewById(R.id.detailsET);
        nameET = findViewById(R.id.nameET);
        addressET = findViewById(R.id.addressET);
        mobileNoET = findViewById(R.id.mobileNoET);
        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
    }

    public void backBtn(View view) {
        onBackPressed();
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
            final ProgressDialog mDialog = new ProgressDialog(PublicHeadingSaveActivity.this);
            mDialog.setMessage("Please waiting...");
            mDialog.show();

           // int id = Integer.parseInt(Common.USER_ID);
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
                        Toast.makeText(PublicHeadingSaveActivity.this, "Congratulations!! Your data saved successfully", Toast.LENGTH_LONG).show();
                        mDialog.dismiss();


                    } else if (response.code() == 203) {
                        Toast.makeText(PublicHeadingSaveActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 401) {
                        Toast.makeText(PublicHeadingSaveActivity.this, "Unauthorized Access", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 422) {
                        Toast.makeText(PublicHeadingSaveActivity.this, "Validation Error", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<PublicHearingSaveResponse> call, Throwable t) {

                    Toast.makeText(PublicHeadingSaveActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            });
        }
    }

    public void btnSubmit(View view) {
        createPublicHearing();
    }

}