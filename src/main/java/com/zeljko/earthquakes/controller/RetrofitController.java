package com.zeljko.earthquakes.controller;

import com.zeljko.earthquakes.entity.Data;
import com.zeljko.earthquakes.entity.Data2;
import com.zeljko.earthquakes.entity.DataArray;
import com.zeljko.earthquakes.entity.Quake;
import com.zeljko.earthquakes.service.QuakeService;
import com.zeljko.earthquakes.service.RetrofitService;
import com.zeljko.earthquakes.service.RetrofitServiceGenerator;
import com.zeljko.earthquakes.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

@Controller
public class RetrofitController {

    private ArrayList<Data2> data2;
    private Data data;
    private QuakeService quakeService;
    private RetrofitService retrofitService;

    @Autowired
    public RetrofitController(Data data, QuakeService quakeService, RetrofitService retrofitService) {
        this.data = data;
        this.quakeService = quakeService;
        this.retrofitService = retrofitService;
    }

    public void loadData() {

        retrofitService = RetrofitServiceGenerator.createService(RetrofitService.class);

        Call<DataArray> callSync = retrofitService.getDataDetails("5", "time");
        Call<DataArray> callAsync = retrofitService.getDataDetails("5", "time");

        callSync.enqueue(new Callback<DataArray>() {
            @Override
            public void onResponse(Call<DataArray> call, Response<DataArray> response) {

                /*DataArray dataArray2 = response.body();
                data2 = dataArray2.features;*/

                data2 = response.body().features;

                for (int i = 0; i < data2.size(); i++) {

                    data.setPlace(data2.get(i).getProperties().getPlace());
                    data.setUrl(data2.get(i).getProperties().getUrl());
                    data.setMag(data2.get(i).getProperties().getMag());
                    data.setTime(data2.get(i).getProperties().getTime());

                    // Create a new Date object from the time in milliseconds of the earthquake
                    Date dateObject = new Date(data.getTime());

                    // Format the date string (i.e. "Mar 3, 1984")
                    String formattedDate = Util.formatDate(dateObject);

                    // Format the time string (i.e. "4:30PM")
                    String formattedTime = Util.formatTime(dateObject);

                    Quake quake = new Quake(
                            data.getMag(), formattedDate, formattedTime, data.getPlace(), data.getUrl());

                    quakeService.save(quake);
                }
            }

            @Override
            public void onFailure(Call<DataArray> call, Throwable throwable) {
                Logger.getLogger("Error", throwable.getMessage());
            }
        });

        // Execute the call asynchronously. Get a positive or negative callback.
        callAsync.enqueue(new Callback<DataArray>() {
            @Override
            public void onResponse(Call<DataArray> call, Response<DataArray> response) {
                DataArray dataArray = response.body();
                //System.out.println(dataArray);
            }

            @Override
            public void onFailure(Call<DataArray> call, Throwable throwable) {
                System.out.println(throwable);
            }
        });


    }


}

