package com.zeljko.earthquakes.service;

import com.zeljko.earthquakes.entity.DataArray;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("fdsnws/event/1/query?format=geojson&limit=100")
    Call<DataArray> getDataDetails(@Query("minmag") String minMagnitude, @Query("orderby") String orderBy);

}