package com.example.kurs.SerwerPogodynkowy.service;

import java.util.concurrent.ThreadLocalRandom;

public class Forecaster implements ForecastService{
    private String[] regionArray;
    private Integer auraRandomUpperBound = 650;

    public Forecaster(String[] regionArray) {
        this.regionArray = regionArray;
    }

    @Override
    public Forecast getForecast(Integer region, Integer aura) {
        return new Forecast(regionArray[region], aura);
    }

    @Override
    public Forecast getForecast(Integer region) {
        return new Forecast(regionArray[region], getRandomInt(auraRandomUpperBound));
    }

    @Override
    public Forecast getForecast() {
        return new Forecast(regionArray[getRandomInt(regionArray.length)], getRandomInt(auraRandomUpperBound));
    }

    private static int getRandomInt(int upperBound) {
        // statyczna metoda klasy ThreadLocalRandom - upperBound jest non-exclusive a wiec ponizej mamy 0 <= X < upperBound
        int randomInt = ThreadLocalRandom.current().nextInt(0, upperBound);
        return randomInt;
    }
}
