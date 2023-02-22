package com.example.weatherstationspring.domain.measurement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@ToString
public class Measurement {
    Long id;
    OffsetDateTime timestamp;

    Float temperature;
    Float pressureHpa;
    Float relativeHumidityInPercent;

    Integer pm1Particles;
    Integer pm25Particles;
    Integer pm10Particles;
    String airQualityByPM;

    Double gpsLatitude;
    Double gpsLongitude;
    //String googleMapsZoom;

    // KOORDYNATY DO GPS Z GOOGLA:
    // 51.7930425 - w tym formacie N
    // @51.7930883,19.4229841,21z (1 - N -> wiecej gora, mniej dol)
    // drugi E (wiecej prawo, mniej lewo)
    // Zoom ok 20 - 21 powinien być na stale
}
