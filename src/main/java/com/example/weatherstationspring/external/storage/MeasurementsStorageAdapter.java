package com.example.weatherstationspring.external.storage;

import com.example.weatherstationspring.domain.measurement.MeasurementsRepository;
import com.example.weatherstationspring.domain.measurement.model.Measurement;
import com.example.weatherstationspring.domain.measurement.model.PageMeasurements;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log
public class MeasurementsStorageAdapter implements MeasurementsRepository {

    private final JpaMeasurementsRepository measurementsRepository;
    private final MeasurementEntityMapper mapper;

    @Override
    public Measurement save(final Measurement measurement) {
        return mapper.toDomain(measurementsRepository.save(mapper.toEntity(measurement)));
    }

    @Override
    public void update(final Measurement measurement) {
        measurementsRepository
                .findById(measurement.getId())
                .ifPresent(quizEntity -> measurementsRepository.save(mapper.toEntity(measurement)));
    }

    @Override
    public void remove(final Long id) {
        measurementsRepository.deleteById(id);
    }

    @Override
    public Optional<Measurement> findById(final Long id) {
        return measurementsRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public PageMeasurements findAll(final Pageable pageable) {
        Page<MeasurementEntity> pageOfTemperatureEntity = measurementsRepository.findAll(pageable);
        List<Measurement> temperaturesOnCurrentPage = pageOfTemperatureEntity.getContent().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
        return new PageMeasurements(
                temperaturesOnCurrentPage,
                pageable.getPageNumber() + 1,
                pageOfTemperatureEntity.getTotalPages(),
                pageOfTemperatureEntity.getTotalElements()
        );
    }
}
