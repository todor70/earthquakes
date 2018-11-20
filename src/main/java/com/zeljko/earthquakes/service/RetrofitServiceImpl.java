package com.zeljko.earthquakes.service;

import com.zeljko.earthquakes.entity.DataArray;
import org.springframework.stereotype.Service;
import retrofit2.Call;

@Service
public class RetrofitServiceImpl implements RetrofitService {
    @Override
    public Call<DataArray> getDataDetails(String minMagnitude, String orderBy) {
        return null;
    }
}