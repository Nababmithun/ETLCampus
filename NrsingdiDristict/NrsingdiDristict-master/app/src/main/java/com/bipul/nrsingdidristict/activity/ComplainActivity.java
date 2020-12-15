package com.bipul.nrsingdidristict.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bipul.nrsingdidristict.ComplainDivisionsSpinnerItem;
import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.adapter.ComplainDivisionsSpinnerAdapter;
import com.bipul.nrsingdidristict.common.Common;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelForAppointmentSubjectGET.AppointmentSubjecResponse;
import com.bipul.nrsingdidristict.modelForAppointmentSubjectGET.Datum;
import com.bipul.nrsingdidristict.modelForComplainSavePOST.ComplainSaveResponse;
import com.bipul.nrsingdidristict.modelPublicHeadingPOST.PublicHearingSaveResponse;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplainActivity extends AppCompatActivity {

    private ImageView getImageIV;
    private LinearLayout selectedIV;
    private int subjectId;

    EditText subjectET, complainDetailsET, nameOneET, addressOneET, nameTwoET, addressTwoET, phoneNumberET;
    String subject, complainDetails, nameOne, addressOne, nameTwo, addressTwo, phoneNumber;
    private ApiInterface apiInterface;
    private Spinner complainOfDivisionsSpinner;

    private ArrayList<ComplainDivisionsSpinnerItem> complainDivisionsSpinnerItems;

    private ComplainDivisionsSpinnerAdapter complainDivisionsSpinnerAdapter;


    private ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        initField();

        fetchComplainOfDivisions();
    }

    private void fetchComplainOfDivisions() {
        apiService = RetrofitClient.getRetrofit().create(ApiInterface.class);
        apiService.getComplainOfDivisions(Common.APP_KEY).enqueue(new Callback<AppointmentSubjecResponse>() {
            @Override
            public void onResponse(Call<AppointmentSubjecResponse> call, Response<AppointmentSubjecResponse> response) {
                if (response.isSuccessful()) {

                    AppointmentSubjecResponse complainOfDivisionsResponse = response.body();
                    List<Datum> datas = complainOfDivisionsResponse.getData();
                    complainDivisionsSpinnerItems = new ArrayList<>();

                    for (int i = 0; i < datas.size(); i++) {

                        subjectId = datas.get(i).getId();
                        complainDivisionsSpinnerItems.add(new ComplainDivisionsSpinnerItem(datas.get(i).getName()));

                    }

                    //Toast.makeText(ComplainActivity.this, "dadadad"+ datas.get(i).getId(), Toast.LENGTH_SHORT).show();
                    complainDivisionsSpinnerAdapter = new ComplainDivisionsSpinnerAdapter(ComplainActivity.this, complainDivisionsSpinnerItems);
                    complainOfDivisionsSpinner.setAdapter(complainDivisionsSpinnerAdapter);

                    complainOfDivisionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            //fetchDistricts(i + 1);
                            String place = adapterView.getItemAtPosition(i).toString();
//                            fetchComplainOfTypes(i + 1);
//                            complainOfDivisions = i + 1;
                            // Toast.makeText(ComplainActivity.this, "position: "+place, Toast.LENGTH_SHORT).show();
                            Toast.makeText(ComplainActivity.this, "dadadad" + datas.get(i).getId(), Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<AppointmentSubjecResponse> call, Throwable t) {

            }
        });


    }

    private void initField() {

        selectedIV = findViewById(R.id.selectedIV);
        getImageIV = findViewById(R.id.getImageIV);
        complainOfDivisionsSpinner = findViewById(R.id.complainOfDivisionsSpinner);
        complainDetailsET = findViewById(R.id.complainDetailsET);
        nameOneET = findViewById(R.id.nameOneET);
        addressOneET = findViewById(R.id.addressOneET);
        nameTwoET = findViewById(R.id.nameTwoET);
        addressTwoET = findViewById(R.id.addressTwoET);
        phoneNumberET = findViewById(R.id.phoneNumberET);

        selectedIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeImage();
            }
        });
    }

    private void takeImage() {
        ImagePicker.Companion.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if (uri != null) {
                getImageIV.setImageURI(uri);
                getImageIV.setVisibility(View.VISIBLE);
            } else {
                getImageIV.setVisibility(View.GONE);
            }
        }
    }

    public void backBtn(View view) {
        onBackPressed();
    }


    private void createPublicHearing() {

        complainDetails = complainDetailsET.getText().toString().trim();
        nameOne = nameOneET.getText().toString().trim();
        addressOne = addressOneET.getText().toString().trim();
        nameTwo = nameTwoET.getText().toString().trim();
        addressTwo = addressTwoET.getText().toString().trim();
        phoneNumber = phoneNumberET.getText().toString().trim();

        if (complainDetails.isEmpty()) {
            complainDetailsET.setError("required");
            complainDetailsET.requestFocus();

        } else if (nameOne.isEmpty()) {
            nameOneET.setError("required");
            nameOneET.requestFocus();
        }
        else if (addressOne.isEmpty()) {
            addressOneET.setError("required");
            addressOneET.requestFocus();
        }
        else if (phoneNumber.isEmpty()) {
            phoneNumberET.setError("required");
            phoneNumberET.requestFocus();
        }else {

            final ProgressDialog mDialog = new ProgressDialog(ComplainActivity.this);
            mDialog.setMessage("Please waiting...");
            mDialog.show();
            apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
            //   int id = Integer.parseInt(Common.USER_ID);
            Call<ComplainSaveResponse> call = apiInterface.setComplain(Common.APP_KEY, 221212121, subjectId, nameOne
                    , nameTwo, phoneNumber, complainDetails, addressOne,
                    addressTwo, "");

            call.enqueue(new Callback<ComplainSaveResponse>() {
                @Override
                public void onResponse(Call<ComplainSaveResponse> call, Response<ComplainSaveResponse> response) {

                    if (response.code() == 200) {
                        ComplainSaveResponse meg = response.body();

                        Toast.makeText(ComplainActivity.this, "subject Id: "+subjectId, Toast.LENGTH_SHORT).show();

                        // Toast.makeText(SignInActivity.this, ""+userAssessToken, Toast.LENGTH_LONG).show();
                        Toast.makeText(ComplainActivity.this, "Congratulations!! Your complain has saved successfully ", Toast.LENGTH_LONG).show();
                        mDialog.dismiss();

                        nameOneET.setText("");
                        nameTwoET.setText("");
                        addressOneET.setText("");
                        addressTwoET.setText("");
                        phoneNumberET.setText("");
                        complainDetailsET.setText("");


                    } else if (response.code() == 203) {
                        Toast.makeText(ComplainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 401) {
                        Toast.makeText(ComplainActivity.this, "Unauthorized Access", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 422) {
                        Toast.makeText(ComplainActivity.this, "Validation Error", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ComplainSaveResponse> call, Throwable t) {

                    Toast.makeText(ComplainActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            });

        }



//       if (complainDetails.isEmpty()) {
//            complainDetailsET.setError("required");
//            complainDetailsET.requestFocus();
//        } else if (nameTwo.isEmpty()) {
//            nameOneET.setError("required");
//            nameOneET.requestFocus();
//        }
//        else if (addressTwo.isEmpty()) {
//            addressTwoET.setError("required");
//            addressTwoET.requestFocus();
//        }
//
//        else {
//
//        }
    }

    public void btnSubmit(View view) {
        createPublicHearing();
    }

}
