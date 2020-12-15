package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.adapterForAPI.ComplainFetchAllAdapter;
import com.bipul.nrsingdidristict.adapterForAPI.EmployeeAdapter;
import com.bipul.nrsingdidristict.common.Common;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelForComplainFechAllGET.ComplainResponse;
import com.bipul.nrsingdidristict.modelForComplainFechAllGET.Datum;
import com.bipul.nrsingdidristict.modelForEmployeeGET.EmployeeResponse;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplainFetchAllActivity extends AppCompatActivity {

    private RecyclerView complainsRV;
    private ComplainFetchAllAdapter complainFetchAllAdapter;
    private List<Datum> complainList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_fetch_all);

        initSwipeLayout();
        loadDataFromAPI();
    }

    private void loadDataFromAPI() {
        apiService = RetrofitClient.getRetrofit().create(ApiInterface.class);

        Integer i = 1;

        apiService.getComplainResponse(Common.APP_KEY, 1).enqueue(new Callback<ComplainResponse>() {
            @Override
            public void onResponse(Call<ComplainResponse> call, Response<ComplainResponse> response) {

                if (response.isSuccessful()) {

                    ComplainResponse complainResponse = response.body();
                    complainList = complainResponse.getData();
                    initComplains();
                    swipeRefreshLayout.setRefreshing(false);

                    Toast.makeText(ComplainFetchAllActivity.this, "" + complainResponse.getData().get(0).getSubjectId().toString(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ComplainFetchAllActivity.this, "server problem", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ComplainResponse> call, Throwable t) {
                Toast.makeText(ComplainFetchAllActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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

    private void initComplains() {
        complainsRV = findViewById(R.id.complainsRV);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        complainFetchAllAdapter = new ComplainFetchAllAdapter(this, complainList);
        complainsRV.setLayoutManager(layoutManager);
        complainsRV.setAdapter(complainFetchAllAdapter);
        swipeRefreshLayout.setRefreshing(false);
        complainFetchAllAdapter.notifyDataSetChanged();

    }

    public void backBtn(View view) {
        onBackPressed();
    }
}