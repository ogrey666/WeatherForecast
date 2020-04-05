package com.example.kurs.SerwisKlienckiPogodynkowy.service;

import com.example.kurs.SerwisKlienckiPogodynkowy.model.WeatherDTO;
import com.example.kurs.SerwisKlienckiPogodynkowy.repository.WeatherGateway;
import org.springframework.stereotype.Service;

@Service
public class WeatherForecastService {

    WeatherGateway weatherGateway;

    public WeatherForecastService(WeatherGateway weatherGateway) {
        this.weatherGateway = weatherGateway;
    }

    public String getForecast(Integer regionId, Integer aura) {
        WeatherDTO weatherDTO = weatherGateway.getWeatherForecast(regionId, aura);
        return "Powietrze w regionie " + weatherDTO.getRegion()
                + " jest " + weatherDTO.getDescription()
                + " z wskaźnikiem PM 2.5 na poziomie " + weatherDTO.getAura()
                + ", czyli dość " + (weatherDTO.getAura() < 50 ? "niskim" : "wysokim");
    }
}
