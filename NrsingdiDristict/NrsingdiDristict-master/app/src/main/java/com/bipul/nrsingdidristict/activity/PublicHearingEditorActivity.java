package com.bipul.nrsingdidristict.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.common.Common;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelForAppoinmentSubjectSavePOST.AppointmentSubjectSaveResponse;
import com.bipul.nrsingdidristict.modelForAppointmentSubjectDeletePOST.AppointmentSubjectDeleteResponse;
import com.bipul.nrsingdidristict.modelForAppointmentSubjectUpdatePOST.AppointmentSubjectUpdateResponse;
import com.bipul.nrsingdidristict.modelForPublicHearingDeletePOST.SetPublicHearingDeleteResponse;
import com.bipul.nrsingdidristict.modelPublicHeadingGET.Datum;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicHearingEditorActivity extends AppCompatActivity {


    private EditText nameET,mobileNoET,addressET,titleET,descriptionET;



    private Datum dataClass = null;
    private String name,mobileNo,address,title,description;
    private int id;

    private Menu action;
    private Bitmap bitmap;

    private ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_hearing_public);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        nameET = findViewById(R.id.nameET);
        mobileNoET = findViewById(R.id.mobileET);
        addressET = findViewById(R.id.addressET);
        titleET = findViewById(R.id.titleET);
        descriptionET = findViewById(R.id.descriptionET);

//        Intent intent = getIntent();
//        id = intent.getIntExtra("id", 0);
//        name = intent.getStringExtra("name");

        Intent i = getIntent();
        dataClass = (Datum) i.getSerializableExtra("allData");

        name = dataClass.getName();
        mobileNo = dataClass.getMobileNo();
        address = dataClass.getAddress();
        id = dataClass.getId();
        title = dataClass.getSubject();
        description = dataClass.getDescription();


        setDataFromIntentExtra();

    }

    private void setDataFromIntentExtra() {

        if (id != 0) {
            readMode();
            getSupportActionBar().setTitle("Edit");
            nameET.setText(name);
            mobileNoET.setText(mobileNo);
            addressET.setText(address);
            titleET.setText(title);
            descriptionET.setText(description);

        } else {
           getSupportActionBar().setTitle("Add");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id == 0){
            action.findItem(R.id.menu_edit).setVisible(false);
            action.findItem(R.id.menu_delete).setVisible(false);
            action.findItem(R.id.menu_save).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();

                return true;
            case R.id.menu_edit:
                //Edit
//                editMode();
//
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(mName, InputMethodManager.SHOW_IMPLICIT);
//
//                action.findItem(R.id.menu_edit).setVisible(false);
//                action.findItem(R.id.menu_delete).setVisible(false);
//                action.findItem(R.id.menu_save).setVisible(true);
//
//                return true;
            case R.id.menu_save:
//                //Save
//                if (id == 0) {
//                    if (TextUtils.isEmpty(nameET.getText().toString()) ){
//                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//                        alertDialog.setMessage("Please complete the field!");
//                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                        alertDialog.show();
//                    }
//                    else {
//                        postData("insert");
//                        action.findItem(R.id.menu_edit).setVisible(true);
//                        action.findItem(R.id.menu_save).setVisible(false);
//                        action.findItem(R.id.menu_delete).setVisible(true);
//                        readMode();
//                    }
//                } else {
//                    updateData("update", id);
//                    action.findItem(R.id.menu_edit).setVisible(true);
//                    action.findItem(R.id.menu_save).setVisible(false);
//                    action.findItem(R.id.menu_delete).setVisible(true);
//                    readMode();
//                }
                return true;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(PublicHearingEditorActivity.this);
                dialog.setMessage("Delete this item?");
                dialog.setPositiveButton("Yes" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData(id);
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private void postData(final String key) {
//        String name = mName.getText().toString().trim();
//
//        if (name.isEmpty()) {
//            mName.setError("required");
//            mName.requestFocus();
//        } else {
//            final ProgressDialog mDialog = new ProgressDialog(PublicHearingEditorActivity.this);
//            mDialog.setMessage("Please waiting...");
//            mDialog.show();
//
//            apiService = RetrofitClient.getRetrofit().create(ApiInterface.class);
//
//            Call<AppointmentSubjectSaveResponse> call = apiService.setAppointmentSubjectSaveResponse(Common.APP_KEY, "1", name);
//
//            call.enqueue(new Callback<AppointmentSubjectSaveResponse>() {
//                @Override
//                public void onResponse(Call<AppointmentSubjectSaveResponse> call, Response<AppointmentSubjectSaveResponse> response) {
//                    if (response.code() == 200) {
//                        AppointmentSubjectSaveResponse serverResponse = response.body();
//                        Toast.makeText(PublicHearingEditorActivity.this, "" + serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        mDialog.dismiss();
//                    } else if (response.code() == 203) {
//                        Toast.makeText(PublicHearingEditorActivity.this, "Fail", Toast.LENGTH_SHORT).show();
//                        mDialog.dismiss();
//                    } else if (response.code() == 401) {
//                        Toast.makeText(PublicHearingEditorActivity.this, "Unauthorized Access", Toast.LENGTH_SHORT).show();
//                        mDialog.dismiss();
//                    } else if (response.code() == 422) {
//                        Toast.makeText(PublicHearingEditorActivity.this, "Validation Error", Toast.LENGTH_SHORT).show();
//                        mDialog.dismiss();
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<AppointmentSubjectSaveResponse> call, Throwable t) {
//                    mDialog.dismiss();
//                }
//            });
//
//        }
//    }
//
//    private void updateData(final String key, final int id) {
//
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Updating...");
//        progressDialog.show();
//        readMode();
//        String name = mName.getText().toString().trim();
//
//        apiService = RetrofitClient.getRetrofit().create(ApiInterface.class);
//
//        Call<AppointmentSubjectUpdateResponse> call = apiService.setAppointmentSubjectUpdateResponse(Common.APP_KEY, id,"1", name);
//
//        call.enqueue(new Callback<AppointmentSubjectUpdateResponse>() {
//            @Override
//            public void onResponse(Call<AppointmentSubjectUpdateResponse> call, Response<AppointmentSubjectUpdateResponse> response) {
//
//                progressDialog.dismiss();
//
//                Log.i(PublicHearingEditorActivity.class.getSimpleName(), response.toString());
//
//                if (response.code()==200){
//                    Toast.makeText(PublicHearingEditorActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AppointmentSubjectUpdateResponse> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(PublicHearingEditorActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void deleteData( final int id) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();
        readMode();
        //delete operation
        apiService = RetrofitClient.getRetrofit().create(ApiInterface.class);

        apiService.setPublicHearingDeleteResponse(Common.APP_KEY, id, "1").enqueue(new Callback<SetPublicHearingDeleteResponse>() {
            @Override
            public void onResponse(Call<SetPublicHearingDeleteResponse> call, Response<SetPublicHearingDeleteResponse> response) {

                if (response.code() == 200) {
                    Toast.makeText(PublicHearingEditorActivity.this, "" + response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PublicHearingEditorActivity.this, "problem", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SetPublicHearingDeleteResponse> call, Throwable t) {

                Toast.makeText(PublicHearingEditorActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        progressDialog.dismiss();
    }

    @SuppressLint("RestrictedApi")
    void readMode(){

        nameET.setFocusableInTouchMode(false);
        mobileNoET.setFocusableInTouchMode(false);
        addressET.setFocusableInTouchMode(false);
        titleET.setFocusableInTouchMode(false);
        descriptionET.setFocusableInTouchMode(false);
        nameET.setFocusable(false);
        mobileNoET.setFocusable(false);
        addressET.setFocusable(false);
        titleET.setFocusable(false);
        descriptionET.setFocusable(false);

    }

//    private void editMode(){
//        mName.setFocusableInTouchMode(true);
//    }

}
