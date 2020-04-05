package com.example.kurs.SerwerPogodynkowy.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "public", name = "weather_forecast")
public class ForecastEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String region;
    private Integer aura;
    private LocalDateTime creationDate;

    protected ForecastEntity() {
    }

    public ForecastEntity(String region, Integer aura) {
        this.region = region;
        this.aura = aura;
    }

    // przed zapisem do db, ustaw czas na now :)
    @PrePersist
    protected void prePersist() {
        creationDate = LocalDateTime.now();
    }

    public String getRegion() {
        return region;
    }

    public Integer getAura() {
        return aura;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "ForecastEntity{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", aura=" + aura +
                ", creationDate=" + creationDate +
                '}';
    }
}
