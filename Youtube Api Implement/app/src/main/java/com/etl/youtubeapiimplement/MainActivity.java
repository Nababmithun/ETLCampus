package com.etl.youtubeapiimplement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.etl.youtubeapiimplement.Interface.ApiInterface;
import com.etl.youtubeapiimplement.Interface.OnItemClickListener;
import com.etl.youtubeapiimplement.model.Datum;
import com.etl.youtubeapiimplement.model.VedioResponse;
import com.etl.youtubeapiimplement.webApi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private YoutubeAdapter youtubeAdapter;
    private List<Datum> videoList = new ArrayList<>();
    private ApiInterface apiInterface;
    String mailLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
        recyclerView = findViewById(R.id.recyclerView);
      //  Datum datum = new Datum("https://www.youtube.com/embed/PRpsvXC8AWs");
      /*  videoList.add(new Datum(R.drawable.media));
        videoList.add(new Datum(R.drawable.media));
        videoList.add(new Datum(R.drawable.media));
        videoList.add(new Datum(R.drawable.media));
        videoList.add(new Datum(R.drawable.media));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        youtubeAdapter = new YoutubeAdapter(getApplicationContext(), videoList);
        recyclerView.setAdapter(youtubeAdapter);
        youtubeAdapter.notifyDataSetChanged();*/
     mailLink = "https://www.youtube.com/embed/";
        getAllVideos();




    }


    private void getAllVideos() {
        //event api
        apiInterface.getVideoResponse().enqueue(new Callback<VedioResponse>() {
            @Override
            public void onResponse(Call<VedioResponse> call, Response<VedioResponse> response) {

                VedioResponse vedioResponse = response.body();

                if (response.code() == 200) {
                    videoList = vedioResponse.getData();

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    youtubeAdapter = new YoutubeAdapter(getApplicationContext(), videoList);
                    recyclerView.setAdapter(youtubeAdapter);
                    youtubeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<VedioResponse> call, Throwable t) {

            }
        });
    }



}
