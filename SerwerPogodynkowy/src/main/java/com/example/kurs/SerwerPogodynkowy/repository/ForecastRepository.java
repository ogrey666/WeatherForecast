package com.example.kurs.SerwerPogodynkowy.repository;

import com.example.kurs.SerwerPogodynkowy.model.ForecastEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastRepository extends CrudRepository<ForecastEntity, Long> {
}
