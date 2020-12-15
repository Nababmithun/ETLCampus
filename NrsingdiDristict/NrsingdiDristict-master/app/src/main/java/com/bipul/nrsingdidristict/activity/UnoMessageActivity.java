package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelForDCMessageGET.DCMessageResponse;
import com.bipul.nrsingdidristict.modelForDCMessageGET.Data;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnoMessageActivity extends AppCompatActivity {

    private TextView messageTV;
    private ApiInterface apiService;
    private List<Data> dataList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uno_message);

        initSwipeLayout();
        loadDataFromAPI();
    }

    private void loadDataFromAPI() {
        apiService = RetrofitClient.getRetrofit().create(ApiInterface.class);
        apiService.getDCMessageResponse("A1b1C2d32564kjhkjadu").enqueue(new Callback<DCMessageResponse>() {
            @Override
            public void onResponse(Call<DCMessageResponse> call, Response<DCMessageResponse> response) {
                if (response.code()==200) {
                    DCMessageResponse dcMessageResponse = response.body();
                    String meg = dcMessageResponse.getData().getMessage();
                    init();
                    messageTV.setText(meg);

                    swipeRefreshLayout.setRefreshing(false);
                    //Toast.makeText(EmployeeActivity.this, ""+employeeList.size(), Toast.LENGTH_SHORT).show();
                }
                else if (response.code() == 203) {
                    Toast.makeText(UnoMessageActivity.this, "server problem", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<DCMessageResponse> call, Throwable t) {

            }
        });
    }

    private void init() {
        messageTV = findViewById(R.id.messageTV);
    }

    public void backBtn(View view) {
        onBackPressed();
    }

    private void initSwipeLayout() {
        //view
        swipeRefreshLayout = findViewById(R.id.employeeSwipeLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //  initSwipeLayout();
                loadDataFromAPI();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        //Default, load for first time
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadDataFromAPI();
                swipeRefreshLayout.setRefreshing(true);
            }

        });

    }
}