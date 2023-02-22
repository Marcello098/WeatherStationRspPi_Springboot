package com.example.weatherstationspring.domain.measurement;

import com.example.weatherstationspring.domain.measurement.exceptions.MeasurementNotFoundException;
import com.example.weatherstationspring.domain.measurement.model.Measurement;
import com.example.weatherstationspring.domain.measurement.model.PageMeasurements;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final ValuesMeasurement valuesMeasurement;

    public Measurement save(Measurement measurement) {
        return measurementsRepository.save(measurement);
    }

    public void update(Measurement measurement) {
        measurementsRepository.update(measurement);
    }

    public void removeById(Long id) {
        measurementsRepository.remove(id);
    }

    public Measurement findById(Long id) {
        return measurementsRepository.findById(id)
                .orElseThrow(MeasurementNotFoundException::new);
    }

    public PageMeasurements findAll(Pageable pageable) {
        return measurementsRepository.findAll(pageable);
    }

    public Measurement measureValuesAndRecordResult() {
        var actualValue = valuesMeasurement.measureValue();
        var savedMeasuredValue = measurementsRepository.save(actualValue);
        return savedMeasuredValue;
    }

    // public Temperature measureTemperatureAndRecordResult() {
    //     var actualTemperature = temperatureMeasurement.measureValue();
    //     var savedTemperature = temperatureRepository.save(actualTemperature);
    //     return savedTemperature;
}
