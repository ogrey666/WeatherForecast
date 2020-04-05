package com.example.kurs.SerwisKlienckiPogodynkowy.controller;

import com.example.kurs.SerwisKlienckiPogodynkowy.service.WeatherForecastService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private WeatherForecastService weatherForecastService;

    public WeatherController(WeatherForecastService weatherForecastService) {
        this.weatherForecastService = weatherForecastService;
    }

    @GetMapping(path = "WeatherForecast")
    public String getForecast(
            @RequestParam(value = "region", required = false) Integer regionId,
            @RequestParam(value = "aura", required = false) Integer aura
    )
    {
        return weatherForecastService.getForecast(regionId, aura);
    }
}