package org.example.weatherstationspring.domain.measurement;

import org.example.weatherstationspring.domain.measurement.model.Measurement;
import org.example.weatherstationspring.domain.measurement.model.PageMeasurements;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MeasurementsRepository {
    Measurement save(Measurement measurement);
    void update(Measurement measurement);
    void remove(Long id);
    Optional<Measurement> findById(Long id);
    PageMeasurements findAll(Pageable pageable);
}
