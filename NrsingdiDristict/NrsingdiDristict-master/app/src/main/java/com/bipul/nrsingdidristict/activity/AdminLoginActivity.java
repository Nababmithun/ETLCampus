package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.common.Common;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelAdminLoginPOST.LoginResponse;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginActivity extends AppCompatActivity {

    private TextInputEditText edtUsername, edtPassword;
    private String username, password;
    private ApiInterface apiInterface;

    //SharedPreferences
    public static final String MyPREFERENCES = "MyPrefs";
    private SharedPreferences sharedpreferences;
    public static final String ASSESS_TOKEN = "assessToken";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String CLIENT_ID = "CLIENT_ID";
    public static final String USER_ID = "USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();

    }

    private void init() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
    }


    private void createLogin() {
        username = edtUsername.getText().toString().trim();
        password = edtPassword.getText().toString().trim();

        if (username.isEmpty()) {
            edtUsername.setError("Please Enter Your Email");
            edtUsername.requestFocus();
            return;
        } else if (password.isEmpty()) {
            edtPassword.setError("Please Enter Your Password.");
            edtPassword.requestFocus();
        } else {
            final ProgressDialog mDialog = new ProgressDialog(AdminLoginActivity.this);
            mDialog.setMessage("Please waiting...");
            mDialog.show();

            Call<LoginResponse> call = apiInterface.setUserInfoForLogin("A1b1C2d32564kjhkjadu", username, password);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if (response.code() == 200) {
                        LoginResponse meg = response.body();

                        int id = meg.getData().getId();


                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(USER_ID, String.valueOf(id));
                        editor.commit();
                        editor.putBoolean("login", true).apply();

                        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                                Context.MODE_PRIVATE);
                        String user_ID = sharedpreferences.getString(USER_ID, "");

                        Common.USER_ID = user_ID;

                        // Toast.makeText(SignInActivity.this, ""+userAssessToken, Toast.LENGTH_LONG).show();
                        Toast.makeText(AdminLoginActivity.this, "Congratulations!! ", Toast.LENGTH_LONG).show();
                        mDialog.dismiss();

                        Intent intent = new Intent(AdminLoginActivity.this, AdminActivity.class);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        startActivity(intent);
                        finish();

                    } else if (response.code() == 203) {
                        Toast.makeText(AdminLoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 401) {
                        Toast.makeText(AdminLoginActivity.this, "Unauthorized Access", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else if (response.code() == 422) {
                        Toast.makeText(AdminLoginActivity.this, "Validation Error", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    Toast.makeText(AdminLoginActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            });
        }
    }

    public void btnSignIn(View view) {
        createLogin();
    }

}