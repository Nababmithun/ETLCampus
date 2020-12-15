package com.bipul.nrsingdidristict.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelPublicHeadingPOST.PublicHearingSaveResponse;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetInformationActivity extends AppCompatActivity {

    private ImageView getImageIV;
    private LinearLayout selectedIV;

    EditText subjectET,detailsET, nameET,addressET, mobileNoET;
    String subject,details,name,address,mobileNo;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_information);

        initField();

        fieldInit();
    }

    private void initField() {
        selectedIV = findViewById(R.id.selectedIV);
        getImageIV = findViewById(R.id.getImageIV);

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

    private void pickFile() {

        new ChooserDialog(this)
                .withStartFile("")
                .withFilter(false, false, "jpg", "jpeg", "png")
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {

                        int file_size = Integer.parseInt(String.valueOf(pathFile.length() / 1024));

                        if (file_size <= 51200) {

                            if(pathFile.exists()){

                             /*   pathFile.getName();
                                filePathTxt.setText( pathFile.getName());
*/
                                Bitmap myBitmap = BitmapFactory.decodeFile(pathFile.getAbsolutePath());
                                if (myBitmap != null){
                                    getImageIV.setImageBitmap(myBitmap);
                                    getImageIV.setVisibility(View.VISIBLE);
                                }else {
                                    getImageIV.setVisibility(View.GONE);
                                }


                            }


                        } else {
                            Toast.makeText(SetInformationActivity.this, "Your File Size is too long. Limit 50MB", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                // to handle the back key pressed or clicked outside the dialog:
                .withOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Log.d("CANCEL", "CANCEL");
                        dialog.cancel(); // MUST have
                    }
                })
                .build()
                .show();
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
            final ProgressDialog mDialog = new ProgressDialog(SetInformationActivity.this);
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
                        Toast.makeText(SetInformationActivity.this, "Congratulations!! Your data saved successfully ", Toast.LENGTH_LONG).show();
                        mDialog.dismiss();


                    } else if (response.code() == 203) {
                        Toast.makeText(SetInformationActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 401) {
                        Toast.makeText(SetInformationActivity.this, "Unauthorized Access", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 422) {
                        Toast.makeText(SetInformationActivity.this, "Validation Error", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<PublicHearingSaveResponse> call, Throwable t) {

                    Toast.makeText(SetInformationActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            });
        }
    }

    public void btnSubmit(View view) {
        createPublicHearing();
    }

}