package com.example.kurs.SerwisKlienckiPogodynkowy.controller;

import com.example.kurs.SerwisKlienckiPogodynkowy.model.WeatherDTO;
import com.example.kurs.SerwisKlienckiPogodynkowy.service.WeatherForecastService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class WeatherController {

    private WeatherForecastService weatherForecastService;

    public WeatherController(WeatherForecastService weatherForecastService) {
        this.weatherForecastService = weatherForecastService;
    }

    @GetMapping(path = "/forecast/generate")
    public String getForecast(
            @RequestParam(value = "region", required = false) Integer regionId,
            @RequestParam(value = "aura", required = false) Integer aura
    )
    {
        return weatherForecastService.getForecast(regionId, aura);
    }

    @GetMapping(path = "/forecasts")
    public List<String> getAllSavedForecasts(
            @RequestParam(value = "region", required = false) Integer regionId,
            @RequestParam(value = "aura", required = false) Integer aura
    )
    {
        return weatherForecastService.getSavedForecasts(regionId, aura);
    }

    @PostMapping(
            path = "/forecast/{id}/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public String updateForecast(
            @PathVariable Long id,
            @RequestBody WeatherDTO weatherDTO
    ) {
        return weatherForecastService.updateForecast(id, weatherDTO);
    }
}