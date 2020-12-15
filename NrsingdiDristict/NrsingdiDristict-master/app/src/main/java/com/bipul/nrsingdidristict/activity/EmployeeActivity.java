package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.adapterForAPI.EmployeeAdapter;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelForEmployeeGET.Datum;
import com.bipul.nrsingdidristict.modelForEmployeeGET.EmployeeResponse;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeActivity extends AppCompatActivity {

    private RecyclerView employeeRV;
    private EmployeeAdapter employeeAdapter;
    private List<Datum> employeeList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

       // initEmployee();
        initSwipeLayout();
        loadDataFromAPI();

    }

    private void loadDataFromAPI() {
        apiService = RetrofitClient.getRetrofit().create(ApiInterface.class);

        apiService.getEmployeeResponse("A1b1C2d32564kjhkjadu").enqueue(new Callback<EmployeeResponse>() {
            @Override
            public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {
                if (response.code()==200) {
                    EmployeeResponse employeeResponse = response.body();
                    employeeList = employeeResponse.getData();
                    initEmployee();
                    swipeRefreshLayout.setRefreshing(false);
                    //Toast.makeText(EmployeeActivity.this, ""+employeeList.size(), Toast.LENGTH_SHORT).show();
                }
                else if (response.code() == 203) {
                    Toast.makeText(EmployeeActivity.this, "server problem", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<EmployeeResponse> call, Throwable t) {

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

    private void initEmployee() {
        employeeRV = findViewById(R.id.employeeRV);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        employeeAdapter = new EmployeeAdapter(this, employeeList);
        employeeRV.setLayoutManager(layoutManager);
        employeeRV.setAdapter(employeeAdapter);
        swipeRefreshLayout.setRefreshing(false);
        employeeAdapter.notifyDataSetChanged();

    }

    public void backBtn(View view) {
        onBackPressed();
    }
}