package com.zeljko.earthquakes.repository;

import com.zeljko.earthquakes.entity.Quake;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuakeRepository extends CrudRepository<Quake, Long>{

    List<Quake> getAllByMagBetween(double mag1, double mag2);
    List<Quake> getAllByMagGreaterThan(double mag);

}
