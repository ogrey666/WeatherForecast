package com.example.kurs.SerwerPogodynkowy.repository;

import com.example.kurs.SerwerPogodynkowy.model.ForecastEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public interface ForecastRepository extends CrudRepository<ForecastEntity, Long> {
    Collection<ForecastEntity> findAll();
    /*
     Query methods, bez implementacji (jpql/native)
     https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    */
    Collection<ForecastEntity> findByAura(Integer aura);
    Collection<ForecastEntity> findByRegion(String region);
    Collection<ForecastEntity> findByRegionAndAura(String region, Integer aura);
    // Update row
    @Modifying
    @Transactional
    @Query("UPDATE ForecastEntity fe SET fe.aura = :aura WHERE fe.id = :id")
    void updateForecastAura(
            @Param("id") Long id,
            @Param("aura") Integer aura
    );

}
