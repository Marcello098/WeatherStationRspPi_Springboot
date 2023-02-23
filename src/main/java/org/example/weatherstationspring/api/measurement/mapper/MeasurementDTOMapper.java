package org.example.weatherstationspring.api.measurement.mapper;

import org.example.weatherstationspring.api.measurement.dto.MeasurementDTO;
import org.example.weatherstationspring.domain.measurement.model.Measurement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementDTOMapper {
    MeasurementDTO toDTO(Measurement domain);

    Measurement toDomain(MeasurementDTO dto);
}
