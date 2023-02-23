package org.example.weatherstationspring.domain.measurement.model;


import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class PageMeasurements implements Serializable {
    List<Measurement> measurements;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}
