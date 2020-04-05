package com.example.kurs.SerwerPogodynkowy.controller;

import com.example.kurs.SerwerPogodynkowy.service.Forecast;
import com.example.kurs.SerwerPogodynkowy.service.ForecastService;
import com.example.kurs.SerwerPogodynkowy.service.Forecaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class WeatherController {

    private ForecastService forecastService;

    @Autowired
    public WeatherController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    // API Endpoint
    @GetMapping(path = "WeatherForecast")
    public Forecast getForecast(
        // Paramsspr
        @RequestParam(value = "region", required = false) Integer region, // voivodeship
        @RequestParam(value = "aura", required = false) Integer aura // PM 2.5 AQI
    ) {
        // Endpoint body
        if (aura != null && aura < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aura param can't go below 0");
        }
        if (region != null && aura != null) {
            return forecastService.getForecast(region, aura);
        } else if (region != null) {
            return forecastService.getForecast(region);
        } else if (aura != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aura param by itself is not supported");
        } else {
            return forecastService.getForecast();
        }
    }


}