package com.example.kurs.SerwerPogodynkowy.config;

import com.example.kurs.SerwerPogodynkowy.service.ForecastService;
import com.example.kurs.SerwerPogodynkowy.service.Forecaster;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@PropertySource(value = "application.properties", encoding="UTF-8") //TODO nie działają polskie znaki w konfiguracji
@Configuration
public class BeanConfiguration {

    @Value("${forecast.region}")
    private String[] region;

    @Bean
    public ForecastService forecastService() {
        return new Forecaster(region);
    }
}