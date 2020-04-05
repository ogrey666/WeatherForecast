package com.example.kurs.SerwerPogodynkowy.service;

import com.example.kurs.SerwerPogodynkowy.transport.ForecastDTO;

public interface ForecastService {
    ForecastDTO getForecast(Integer region, Integer aura);
    ForecastDTO getForecast(Integer region);
    ForecastDTO getForecast();
}
