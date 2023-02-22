package com.example.weatherstationspring.api.measurement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@ToString
public class MeasurementDTO {
    Long id;
    OffsetDateTime timestamp;

    Float temperature;
    Float pressureHpa;
    Float relativeHumidityInPercent;

    Float pm1Particles;
    Float pm25Particles;
    Float pm10Particles;

    Double gpsLatitude;
    Double gpsLongitude;
    //String googleMapsZoom;
}
