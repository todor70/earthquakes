package com.zeljko.earthquakes.service;

import com.zeljko.earthquakes.entity.Quake;
import com.zeljko.earthquakes.repository.QuakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuakeServiceImpl implements QuakeService {

    private QuakeRepository quakeRepository;

    @Autowired
    public QuakeServiceImpl(QuakeRepository quakeRepository) {
        this.quakeRepository = quakeRepository;
    }

    @Override
    public List<Quake> getAllQuakes() {
        return (List<Quake>) quakeRepository.findAll();
    }

    @Override
    public List<Quake> getAllByMagGreaterThan(double mag) {
        return quakeRepository.getAllByMagGreaterThan(mag);
    }

    @Override
    public List<Quake> getAllByMagBetween(double mag1, double mag2) {
        return quakeRepository.getAllByMagBetween(mag1, mag2);
    }

    @Override
    public void save(Quake quake) {
        quakeRepository.save(quake);
    }

}
