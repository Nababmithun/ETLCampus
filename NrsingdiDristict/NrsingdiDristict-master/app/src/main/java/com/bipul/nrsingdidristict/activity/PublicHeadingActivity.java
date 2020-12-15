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
import com.bipul.nrsingdidristict.adapterForAPI.PublicHeadingAdapter;
import com.bipul.nrsingdidristict.common.Common;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelForAppointmentSubjectFetchAllGET.AppointmentSubjectFetchAllResponse;
import com.bipul.nrsingdidristict.modelForEmployeeGET.EmployeeResponse;
import com.bipul.nrsingdidristict.modelPublicHeadingGET.Datum;
import com.bipul.nrsingdidristict.modelPublicHeadingGET.PublicHearingResponse;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicHeadingActivity extends AppCompatActivity {

    private RecyclerView publicHearingRV;
    private PublicHeadingAdapter publicHeadingAdapter;
    private List<Datum> publicHearingList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_heading);



        initSwipeLayout();
        loadDataFromAPI();

    }

    private void loadDataFromAPI() {

        apiService = RetrofitClient.getRetrofit().create(ApiInterface.class);
        apiService.getPublicHearingResponse(Common.APP_KEY,1).enqueue(new Callback<PublicHearingResponse>() {
            @Override
            public void onResponse(Call<PublicHearingResponse> call, Response<PublicHearingResponse> response) {

                PublicHearingResponse publicHearingResponse = response.body();
                if (response.code()==200) {

                    publicHearingList = publicHearingResponse.getData();
                    init();
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(PublicHeadingActivity.this, ""+publicHearingList.get(0).getName(), Toast.LENGTH_SHORT).show();

                }
                else if (response.code() == 203) {
                    Toast.makeText(PublicHeadingActivity.this, publicHearingResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }else if (response.code() == 401){
                    Toast.makeText(PublicHeadingActivity.this, publicHearingResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }else if (response.code() == 422){
                    Toast.makeText(PublicHeadingActivity.this, publicHearingResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<PublicHearingResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }


    public void backBtn(View view) {
        onBackPressed();
    }


    private void init() {
        publicHearingRV = findViewById(R.id.publicHearingRV);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        publicHeadingAdapter = new PublicHeadingAdapter(this, publicHearingList);
        publicHearingRV.setLayoutManager(layoutManager);
        publicHearingRV.setAdapter(publicHeadingAdapter);
        swipeRefreshLayout.setRefreshing(false);
        publicHeadingAdapter.notifyDataSetChanged();

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