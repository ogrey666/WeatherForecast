package com.example.kurs.SerwerPogodynkowy.service;

/*
    Obiekt zwracany przez endpointy
 */
public class Forecast {

    String region;
    Integer aura;
    String description;

    public Forecast(String region, Integer aura) {
        this.region = region;
        this.aura = aura;
    }

    public String getRegion() {
        return region;
    }

    public Integer getAura() {
        return aura;
    }
 /*
    public String getWeatherForecast() {
        return "Dzisiaj w regionie " + region + " indeks AQI wynosi " + aura + ", a więc powietrze jest " + getAirQualityIndexDescription(aura)
    }
*/
    public String getDescription() {
        if (aura < 0) {
            return "Poszło poza skalę, ale jest super!";
        } else if (aura <= 50) {
            return "Dobre";
        } else if (aura <= 100) {
            return "Umiarkowane";
        } else if (aura <= 150) {
            return "Niezdrowe dla wrażliwych osób";
        } else if (aura <= 200) {
            return "Niezdrowe";
        } else if (aura <= 300) {
            return "Bardzo niezdrowe";
        } else if (aura <= 500) {
            return "Dla hazardzistów";
        } else {
            return "Tragicznie";
        }
    }

}
