package org.example.weatherstationspring.api.measurement.mapper;

import org.example.weatherstationspring.api.measurement.dto.MeasurementDTO;
import org.example.weatherstationspring.api.measurement.dto.PageMeasurementDTO;
import org.example.weatherstationspring.domain.measurement.model.Measurement;
import org.example.weatherstationspring.domain.measurement.model.PageMeasurements;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageMeasurementDTOMapper {

    @Mapping(target="measurements", qualifiedByName = "toMeasurementsDTOList")
    PageMeasurementDTO toPageDto(PageMeasurements domain);

    @Named("toMeasurementsDTOList")
    @IterableMapping(qualifiedByName = "measurementToMeasurementDTO")
    List<MeasurementDTO> toListDTO(List<Measurement> measurements);

    @Named("measurementToMeasurementDTO")
    MeasurementDTO toDTO(Measurement domain);
}
