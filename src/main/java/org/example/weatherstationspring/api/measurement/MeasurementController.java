package org.example.weatherstationspring.api.measurement;

import org.example.weatherstationspring.api.measurement.dto.MeasurementDTO;
import org.example.weatherstationspring.api.measurement.dto.PageMeasurementDTO;
import org.example.weatherstationspring.api.measurement.mapper.MeasurementDTOMapper;
import org.example.weatherstationspring.api.measurement.mapper.PageMeasurementDTOMapper;
import org.example.weatherstationspring.domain.measurement.MeasurementsService;
import org.example.weatherstationspring.domain.measurement.model.Measurement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/measurements")
public class MeasurementController {
    private final MeasurementsService measurementsService;
    private final MeasurementDTOMapper measurementDTOMapper;
    private final PageMeasurementDTOMapper pageMeasurementDTOMapper;

    @GetMapping
    public ResponseEntity<PageMeasurementDTO> getMeasurements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageMeasurementDTO pageMeasurements;
        pageMeasurements = pageMeasurementDTOMapper.toPageDto(measurementsService.findAll(pageable));

        return ResponseEntity.ok(pageMeasurements);
    }

    @GetMapping(path = "/{measurementId}")
    public ResponseEntity<MeasurementDTO> getSingleMeasurement(@PathVariable Long measurementId) {
        Measurement measurement = measurementsService.findById(measurementId);
        return ResponseEntity
                .ok(measurementDTOMapper.toDTO(measurement));
    }
    
}
