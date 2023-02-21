package com.example.weatherstationspring.api.measurement.mapper;

import com.example.weatherstationspring.api.measurement.dto.PageMeasurementDTO;
import com.example.weatherstationspring.domain.measurement.model.PageMeasurements;

public interface PageMeasurementDTOMapper {
    PageMeasurementDTO toPageDto(PageMeasurements all);
}
