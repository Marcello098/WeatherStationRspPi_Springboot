package com.example.weatherstationspring.external.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface JpaMeasurementsRepository extends JpaRepository<MeasurementEntity, Long> {

    List<MeasurementEntity> findAllByTimestampStartsWith(OffsetDateTime timestamp);
    List<MeasurementEntity> findAllByAirQualityByPM(String airQualityByPM, Pageable pageable);

    List<MeasurementEntity> findByTimestampStartsWith(OffsetDateTime timestamp);

    List<MeasurementEntity> findByTemperatureBetween(Float temperature1, Float temperature2);

    List<MeasurementEntity> findByPressureHpaBetween(Float pressure1, Float pressure2);

    List<MeasurementEntity> findByRelativeHumidityInPercentBetween(Float relativeHumidity1, Float relativeHumidity2);

    List<MeasurementEntity> findByAirQualityByPM(String airQualityByPM);
    List<MeasurementEntity> findByAirQualityByPMAndTimestampStartingWith(String airQualityByPM, OffsetDateTime timestamp);

}
