package org.example.weatherstationspring.api.measurement.dto;

import lombok.Value;

import java.util.List;
@Value
public class PageMeasurementDTO {
    List<MeasurementDTO> measurements;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}
