package com.example.kurs.SerwerPogodynkowy.service;

import com.example.kurs.SerwerPogodynkowy.model.ForecastEntity;
import com.example.kurs.SerwerPogodynkowy.transport.ForecastDTO;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Collection;
import java.util.Optional;

public interface ForecastService {
    ForecastDTO getForecast(Integer region, Integer aura);
    ForecastDTO getForecast(Integer region);
    ForecastDTO getForecast();
    Collection<ForecastDTO> getAllSavedForecasts(Integer region, Integer aura);
    Collection<ForecastDTO> getAllSavedForecastsForRegion(Integer region);
    Collection<ForecastDTO> getAllSavedForecastsForAura(Integer aura);
    Collection<ForecastDTO> getAllSavedForecasts();
    ForecastDTO updateForecast(Long forecastId, ForecastEntity forecastEntity);
}