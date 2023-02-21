package com.example.weatherstationspring.external.storage;

import com.example.weatherstationspring.domain.measurement.model.Measurement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementEntityMapper {
    MeasurementEntity toEntity(Measurement domain);

    Measurement toDomain(MeasurementEntity entity);
}
