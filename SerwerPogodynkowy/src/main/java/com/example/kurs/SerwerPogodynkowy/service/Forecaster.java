package com.example.kurs.SerwerPogodynkowy.service;

import com.example.kurs.SerwerPogodynkowy.model.ForecastEntity;
import com.example.kurs.SerwerPogodynkowy.repository.ForecastRepository;
import com.example.kurs.SerwerPogodynkowy.transport.ForecastDTO;
import javassist.NotFoundException;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.text.ParseException;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Forecaster implements ForecastService{
    private String[] regionArray;
    private static final Integer auraRandomUpperBound = 650;
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
        // zapis do db
        ForecastEntity forecastEntity = new ForecastEntity(getRegionName(region), aura);
        forecastRepository.save(forecastEntity);
        // koniec zapisu
        return convertToDTO(forecastEntity);
    }

    @Override
    public ForecastDTO getForecast(Integer region) {
        ForecastEntity forecastEntity = new ForecastEntity(getRegionName(region), getRandomInt(auraRandomUpperBound));
        forecastRepository.save(forecastEntity);
        return convertToDTO(forecastEntity);
    }

    @Override
    public ForecastDTO getForecast() {
        ForecastEntity forecastEntity = new ForecastEntity(getRegionName(getRandomInt(regionArray.length)), getRandomInt(auraRandomUpperBound));
        forecastRepository.save(forecastEntity);
        return convertToDTO(forecastEntity);
    }

    @Override
    public Collection<ForecastDTO> getAllSavedForecasts(Integer region, Integer aura) {
        return forecastRepository.findByRegionAndAura(getRegionName(region), aura).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Collection<ForecastDTO> getAllSavedForecastsForRegion(Integer region) {
        return forecastRepository.findByRegion(getRegionName(region)).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Collection<ForecastDTO> getAllSavedForecastsForAura(Integer aura) {
        return forecastRepository.findByAura(aura).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Collection<ForecastDTO> getAllSavedForecasts() {
        return forecastRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ForecastDTO updateForecast(Long forecastId, ForecastEntity forecastEntity) {
        forecastRepository.updateForecastAura(forecastId, forecastEntity.getAura());
        return convertToDTO(forecastRepository.findById(forecastId).get());
    }

    private static int getRandomInt(int upperBound) {
        // statyczna metoda klasy ThreadLocalRandom - upperBound jest non-exclusive a wiec ponizej mamy 0 <= X < upperBound
        int randomInt = ThreadLocalRandom.current().nextInt(0, upperBound);
        return randomInt;
    }

    private String getRegionName(Integer regionID) {
        return regionArray[regionID];
    }

}
