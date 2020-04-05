package com.example.kurs.SerwisKlienckiPogodynkowy.model;

public class WeatherDTO {

    String region;
    Integer aura;
    String description;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getAura() {
        return aura;
    }

    public void setAura(Integer aura) {
        this.aura = aura;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
