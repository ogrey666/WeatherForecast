package com.example.kurs.SerwerPogodynkowy.service;

import com.example.kurs.SerwerPogodynkowy.model.ForecastEntity;
import com.example.kurs.SerwerPogodynkowy.repository.ForecastRepository;
import com.example.kurs.SerwerPogodynkowy.transport.ForecastDTO;

import java.util.concurrent.ThreadLocalRandom;

public class Forecaster implements ForecastService{
    private String[] regionArray;
    private Integer auraRandomUpperBound = 650;
    private ForecastRepository forecastRepository;

    public Forecaster(String[] regionArray, ForecastRepository forecastRepository) {
        this.regionArray = regionArray;
        this.forecastRepository = forecastRepository;
    }


    @Override
    public ForecastDTO getForecast(Integer region, Integer aura) {
        String regionDesc = regionArray[region];
        // zapis do db
        ForecastEntity forecastEntity = new ForecastEntity(regionDesc, aura);
        forecastRepository.save(forecastEntity);
        // koniec zapisu
        return new ForecastDTO(regionDesc, aura);
    }

    @Override
    public ForecastDTO getForecast(Integer region) {
        String regionDesc = regionArray[region];
        Integer aura = getRandomInt(auraRandomUpperBound);
        ForecastEntity forecastEntity = new ForecastEntity(regionDesc, aura);
        forecastRepository.save(forecastEntity);
        return new ForecastDTO(regionDesc, aura);
    }

    @Override
    public ForecastDTO getForecast() {
        String regionDesc = regionArray[getRandomInt(regionArray.length)];
        Integer aura = getRandomInt(auraRandomUpperBound);
        ForecastEntity forecastEntity = new ForecastEntity(regionDesc, aura);
        forecastRepository.save(forecastEntity);
        return new ForecastDTO(regionDesc, aura);
    }

    private static int getRandomInt(int upperBound) {
        // statyczna metoda klasy ThreadLocalRandom - upperBound jest non-exclusive a wiec ponizej mamy 0 <= X < upperBound
        int randomInt = ThreadLocalRandom.current().nextInt(0, upperBound);
        return randomInt;
    }

}
