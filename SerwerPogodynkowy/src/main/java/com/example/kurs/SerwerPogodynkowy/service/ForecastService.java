package com.example.kurs.SerwerPogodynkowy.service;

import com.example.kurs.SerwerPogodynkowy.transport.ForecastDTO;

import java.util.Collection;

public interface ForecastService {
    ForecastDTO getForecast(Integer region, Integer aura);
    ForecastDTO getForecast(Integer region);
    ForecastDTO getForecast();
    Collection<ForecastDTO> getAllSavedForecasts();
}
