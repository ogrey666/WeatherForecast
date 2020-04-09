package com.example.kurs.SerwerPogodynkowy.controller;

import com.example.kurs.SerwerPogodynkowy.model.ForecastEntity;
import com.example.kurs.SerwerPogodynkowy.transport.ForecastDTO;
import com.example.kurs.SerwerPogodynkowy.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class WeatherController {

    private ForecastService forecastService;

    @Autowired
    public WeatherController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }


    // API Endpoint
    @GetMapping(path = "/forecast/generate")
    public ForecastDTO getForecast(
        // Params
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

    @GetMapping(path = "/forecasts")
    public Collection<ForecastDTO> getAllSavedForecasts(
            @RequestParam(value = "region", required = false) Integer region, // voivodeship
            @RequestParam(value = "aura", required = false) Integer aura // PM 2.5 AQI
    ) {
        // Endpoint body
        if (aura != null && aura < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aura param can't go below 0");
        }
        if (region != null && aura != null) {
            return forecastService.getAllSavedForecasts(region, aura);
        } else if (region != null) {
            return forecastService.getAllSavedForecastsForRegion(region);
        } else if (aura != null) {
            return forecastService.getAllSavedForecastsForAura(aura);
        } else {
            return forecastService.getAllSavedForecasts();
        }
    }

    @PostMapping(
            path = "/forecast/{id}/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ForecastDTO updateForecast(
            @PathVariable Long id,
            @RequestBody ForecastEntity forecastEntity
            ) {
        if (forecastEntity.getAura() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide aura param");
        }
        if (forecastEntity.getAura() != null && forecastEntity.getAura() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aura param can't go below 0");
        }
        return forecastService.updateForecast(id, forecastEntity);
    }

}
