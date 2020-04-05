package com.example.kurs.SerwerPogodynkowy.service;

public interface ForecastService {
    Forecast getForecast(Integer region, Integer aura);
    Forecast getForecast(Integer region);
    Forecast getForecast();
}
