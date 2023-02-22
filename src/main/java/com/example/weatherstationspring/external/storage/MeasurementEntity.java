package com.example.weatherstationspring.external.storage;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "measurements")
public class MeasurementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    Long id;

    @Column(name = "timestamp", nullable = false)
    private OffsetDateTime timestamp;

    @Column(name = "temperature_c")
    private Float temperature;

    @Column(name="pressure_hpa")
    private Float pressureHpa;

    @Column(name="relative_humidity_percent")
    private Float relativeHumidityInPercent;


    @Column(name="pm1_ugper3")
    private Integer pm1Particles;

    @Column(name="pm2dot5_ugper3")
    private Integer pm25Particles;

    @Column(name="pm10_ugper3")
    private Integer pm10Particles;

    @Column(name="air_quality")
    private String airQualityByPM;

    @Column(name="gps_latitude")
    private Double gpsLatitude;

    @Column(name="gps_longitude")
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

