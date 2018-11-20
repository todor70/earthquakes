package com.zeljko.earthquakes;

import com.zeljko.earthquakes.controller.RetrofitController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class EarthquakesApplication {


    public static void main(String[] args) {
        SpringApplication.run(EarthquakesApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(RetrofitController retrofitController) {
        return args -> {

            retrofitController.loadData();

        };
    }

}
