package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.common.Common;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelForSMSSendPOST.SMSSendResponse;
import com.bipul.nrsingdidristict.modelPublicHeadingGET.Datum;
import com.bipul.nrsingdidristict.modelPublicHeadingPOST.PublicHearingSaveResponse;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMessageActivity extends AppCompatActivity {

    private EditText  messageET;
    private String phoneNo, message;
    String phoneNumber;
    private ApiInterface apiInterface;
    private Button btnSent;

    TextView phoneNoET;

    Datum dataClass=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        fieldInit();

        Intent i = getIntent();
        dataClass = (Datum) i.getSerializableExtra("info");
        assert dataClass != null;
        phoneNumber = dataClass.getMobileNo();

        phoneNoET.setText(phoneNumber);


        Toast.makeText(this, "" + dataClass.getMobileNo(), Toast.LENGTH_SHORT).show();


    }

    private void fieldInit() {
        phoneNoET = findViewById(R.id.mobileNoET);
        messageET = findViewById(R.id.messageET);
        btnSent = findViewById(R.id.btnSent);
        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);

        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSendSMS();

                Toast.makeText(SendMessageActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnSent(View view) {

    }

    public void backBtn(View view) {
        onBackPressed();
    }



    private void createSendSMS() {

        message = messageET.getText().toString().trim();
        if (message.isEmpty()) {
            messageET.setError("required");
            messageET.requestFocus();
        }else {


            final ProgressDialog mDialog = new ProgressDialog(SendMessageActivity.this);
            mDialog.setMessage("Please waiting...");
            mDialog.show();


            Call<SMSSendResponse> call = apiInterface.setSMSSendResponse("A1b1C2d32564kjhkjadu", 1,phoneNumber,message);

            call.enqueue(new Callback<SMSSendResponse>() {
                @Override
                public void onResponse(Call<SMSSendResponse> call, Response<SMSSendResponse> response) {

                    if (response.code() == 200) {
                        SMSSendResponse meg = response.body();


                        messageET.setText(null);

                        // Toast.makeText(SignInActivity.this, ""+userAssessToken, Toast.LENGTH_LONG).show();
                        Toast.makeText(SendMessageActivity.this, "আপনার বার্তাটি পাঠানো হয়েছে", Toast.LENGTH_LONG).show();
                        mDialog.dismiss();


                    } else if (response.code() == 203) {
                        Toast.makeText(SendMessageActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 401) {
                        Toast.makeText(SendMessageActivity.this, "Unauthorized Access", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 422) {
                        Toast.makeText(SendMessageActivity.this, "Validation Error", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<SMSSendResponse> call, Throwable t) {

                    Toast.makeText(SendMessageActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            });

        }

    }


}