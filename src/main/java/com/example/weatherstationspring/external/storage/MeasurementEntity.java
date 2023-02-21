package com.example.weatherstationspring.external.storage;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeasurementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(name = "TIMESTAMP")
    private OffsetDateTime timestamp;

    @Column(name = "TEMPERATURE_C")
    private Float temperature;

    @Column(name="PRESSURE_HPA")
    private Float pressureHpa;

    @Column(name="RELATIVEHUMIDITY_PERCENT")
    private Float relativeHumidityInPercent;


    @Column(name="PM1_UGPERM3")
    private Integer pm1Particles;

    @Column(name="PM2dot5_UGPERM3")
    private Integer pm25Particles;

    @Column(name="PM10_UGPERM3")
    private Integer pm10Particles;

    @Column(name="AIR_QUALITY")
    private String airQualityByPM;

    @Column(name="GPS_LATITUDE")
    private Double gpsLatitude;

    @Column(name="GPS_LONGTITUDE")
    private Double gpsLongitude;

    // KOORDYNATY DO GPS Z GOOGLA:
    // 51.7930425 - w tym formacie N
    // @51.7930883,19.4229841,21z (1 - N -> wiecej gora, mniej dol)
    // drugi E (wiecej prawo, mniej lewo)
    // Zoom ok 20 - 21 powinien być na stale
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final MeasurementEntity that = (MeasurementEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

