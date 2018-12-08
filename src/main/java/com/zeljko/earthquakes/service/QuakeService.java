package com.zeljko.earthquakes.service;

import com.zeljko.earthquakes.entity.Quake;

import java.util.List;

public interface QuakeService {

    List<Quake> getAllQuakes();

    List<Quake> getAllByMagGreaterThan(double mag);

    List<Quake> getAllByMagBetween(double mag1, double mag2);

    void save (Quake quake);

}
