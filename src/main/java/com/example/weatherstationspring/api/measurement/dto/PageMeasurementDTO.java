package com.example.weatherstationspring.api.measurement.dto;

import lombok.Value;

import java.util.List;
@Value
public class PageMeasurementDTO {
    List<MeasurementDTO> measures;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}
