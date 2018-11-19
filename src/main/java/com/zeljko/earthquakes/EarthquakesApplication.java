package com.zeljko.earthquakes;

import com.zeljko.earthquakes.entity.Data2;
import com.zeljko.earthquakes.entity.DataArray;
import com.zeljko.earthquakes.entity.Quake;
import com.zeljko.earthquakes.repository.QuakeRepository;
import com.zeljko.earthquakes.service.RetrofitService;
import com.zeljko.earthquakes.service.RetrofitServiceGenerator;
import com.zeljko.earthquakes.util.Util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

@SpringBootApplication
public class EarthquakesApplication {

    private ArrayList<Data2> data1;
    private double mag;
    private String place;
    private long time;
    private String url;

    public static void main(String[] args) {
        SpringApplication.run(EarthquakesApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(QuakeRepository quakeRepository) {
        return args -> {

            RetrofitService service = RetrofitServiceGenerator.createService(RetrofitService.class);

            Call<DataArray> callSync = service.getDataDetails("5", "time");
            Call<DataArray> callAsync = service.getDataDetails("5", "time");

            callSync.enqueue(new Callback<DataArray>() {
                @Override
                public void onResponse(Call<DataArray> call, Response<DataArray> response) {

                    DataArray dataArray2 = response.body();
                    data1 = dataArray2.features;

                    for (int i = 0; i < data1.size(); i++) {

                        place = data1.get(i).getProperties().getPlace();
                        url = data1.get(i).getProperties().getUrl();
                        mag = data1.get(i).getProperties().getMag();
                        time = data1.get(i).getProperties().getTime();

                        // Create a new Date object from the time in milliseconds of the earthquake
                        Date dateObject = new Date(time);

                        // Format the date string (i.e. "Mar 3, 1984")
                        String formattedDate = Util.formatDate(dateObject);

                        // Format the time string (i.e. "4:30PM")
                        String formattedTime = Util.formatTime(dateObject);

                        Quake quake = new Quake(
                                mag, formattedDate, formattedTime, place, url);

                        quakeRepository.save(quake);
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


        };
    }

}
