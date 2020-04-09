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
    Collection<ForecastDTO> getSavedForecasts(Integer region, Integer aura);
    Collection<ForecastDTO> getSavedForecastsForRegion(Integer region);
    Collection<ForecastDTO> getSavedForecastsForAura(Integer aura);
    Collection<ForecastDTO> getSavedForecasts();
    ForecastDTO updateForecast(Long forecastId, ForecastEntity forecastEntity);
}