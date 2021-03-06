package com.example.kurs.SerwerPogodynkowy.config;

import com.example.kurs.SerwerPogodynkowy.repository.ForecastRepository;
import com.example.kurs.SerwerPogodynkowy.service.ForecastService;
import com.example.kurs.SerwerPogodynkowy.service.Forecaster;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.ModelMap;
import sun.tools.jar.CommandLine;

//@PropertySource(value = "application.properties", encoding="UTF-8") //TODO nie działają polskie znaki w konfiguracji
@Configuration
public class BeanConfiguration {

    @Value("${forecast.region}")
    private String[] region;

    @Bean
    public ForecastService forecastService(ForecastRepository forecastRepository) {
        return new Forecaster(region, forecastRepository);
    }

//    // Bean do generowanie inputa na starcie
//    @Bean
//    public CommandLineRunner generuj(ForecastService forecastService) {
//        return args -> {
//            forecastService.getForecast();
//            forecastService.getForecast();
//            forecastService.getForecast();
//            forecastService.getForecast();
//            forecastService.getForecast();
//            forecastService.getForecast();
//        };
//
//    }

}
