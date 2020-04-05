package com.example.kurs.SerwerPogodynkowy.service;

import com.example.kurs.SerwerPogodynkowy.model.ForecastEntity;
import com.example.kurs.SerwerPogodynkowy.repository.ForecastRepository;
import com.example.kurs.SerwerPogodynkowy.transport.ForecastDTO;

import java.text.ParseException;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Forecaster implements ForecastService{
    private String[] regionArray;
    private Integer auraRandomUpperBound = 650;
    private ForecastRepository forecastRepository;

    public Forecaster(String[] regionArray, ForecastRepository forecastRepository) {
        this.regionArray = regionArray;
        this.forecastRepository = forecastRepository;
    }

    private ForecastDTO convertToDTO(ForecastEntity forecastEntity) {
        ForecastDTO forecastDTO = new ForecastDTO(forecastEntity.getRegion(), forecastEntity.getAura());//modelMapper.map(forecastEntity, ForecastDTO.class);
        return forecastDTO;
    }

    //TODO
    private ForecastEntity convertToEntity(ForecastDTO forecastDTO) throws ParseException {
        ForecastEntity forecastEntity = new ForecastEntity(forecastDTO.getRegion(), forecastDTO.getAura());//modelMapper.map(forecastDTO, ForecastEntity.class);
        return forecastEntity;
    }

    @Override
    public ForecastDTO getForecast(Integer region, Integer aura) {
        String regionDesc = regionArray[region];
        // zapis do db
        ForecastEntity forecastEntity = new ForecastEntity(regionDesc, aura);
        forecastRepository.save(forecastEntity);
        // koniec zapisu
        return convertToDTO(forecastEntity);
    }

    @Override
    public ForecastDTO getForecast(Integer region) {
        String regionDesc = regionArray[region];
        Integer aura = getRandomInt(auraRandomUpperBound);
        ForecastEntity forecastEntity = new ForecastEntity(regionDesc, aura);
        forecastRepository.save(forecastEntity);
        return convertToDTO(forecastEntity);
    }

    @Override
    public ForecastDTO getForecast() {
        String regionDesc = regionArray[getRandomInt(regionArray.length)];
        Integer aura = getRandomInt(auraRandomUpperBound);
        ForecastEntity forecastEntity = new ForecastEntity(regionDesc, aura);
        forecastRepository.save(forecastEntity);
        return convertToDTO(forecastEntity);
    }

    @Override
    public Collection<ForecastDTO> getAllSavedForecasts() {
        return forecastRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private static int getRandomInt(int upperBound) {
        // statyczna metoda klasy ThreadLocalRandom - upperBound jest non-exclusive a wiec ponizej mamy 0 <= X < upperBound
        int randomInt = ThreadLocalRandom.current().nextInt(0, upperBound);
        return randomInt;
    }

}
