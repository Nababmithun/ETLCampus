package com.etl.youtubeapiimplement.Interface;
import com.etl.youtubeapiimplement.model.VedioResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("api/video")
    Call<VedioResponse> getVideoResponse();


}
