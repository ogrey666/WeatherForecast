package com.example.kurs.SerwisKlienckiPogodynkowy.service;

import com.example.kurs.SerwisKlienckiPogodynkowy.model.WeatherDTO;
import com.example.kurs.SerwisKlienckiPogodynkowy.repository.WeatherGateway;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class WeatherForecastService {

    WeatherGateway weatherGateway;

    public WeatherForecastService(WeatherGateway weatherGateway) {
        this.weatherGateway = weatherGateway;
    }

    private String buildClientResponse(WeatherDTO weatherDTO) {
        return "Powietrze w regionie " + weatherDTO.getRegion()
                + " jest " + weatherDTO.getDescription()
                + " z wskaźnikiem PM 2.5 na poziomie " + weatherDTO.getAura()
                + ", czyli dość " + (weatherDTO.getAura() < 50 ? "niskim" : "wysokim");
    }

    public String getForecast(Integer regionId, Integer aura) {
        WeatherDTO weatherDTO = weatherGateway.getForecast(regionId, aura);
        return buildClientResponse(weatherDTO);
    }

    public List<String> getSavedForecasts(Integer regionId, Integer aura) {
        Collection<WeatherDTO> weatherResponse = weatherGateway.getSavedForecasts(regionId, aura);
        List<String> stringList = new ArrayList<String>();
        for (WeatherDTO weatherDTO : weatherResponse
             ) {
            stringList.add(buildClientResponse(weatherDTO));
        }
        return stringList;
    }

    public String updateForecast(Long forecastId, WeatherDTO weatherDTO) {
        WeatherDTO weatherDTO1 = weatherGateway.updateForecast(forecastId, weatherDTO);
        return buildClientResponse(weatherDTO1);
    }
}
