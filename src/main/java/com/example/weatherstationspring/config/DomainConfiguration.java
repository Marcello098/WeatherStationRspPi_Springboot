package com.example.weatherstationspring.config;

import com.example.weatherstationspring.domain.measurement.MeasurementsRepository;
import com.example.weatherstationspring.domain.measurement.MeasurementsService;
import com.example.weatherstationspring.domain.measurement.ValuesMeasurement;
import com.example.weatherstationspring.external.measurement.temporaryFakeAdapter.FakeTemperatureMeasurementAdapter;
import com.example.weatherstationspring.external.storage.MeasurementEntityMapper;
import com.example.weatherstationspring.external.storage.MeasurementsStorageAdapter;
import com.example.weatherstationspring.external.storage.PagingAndSortingMeasurementsRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("domain.properties")
class DomainConfiguration {

    @Bean
    public ValuesMeasurement valuesMeasurement() {
        return new FakeTemperatureMeasurementAdapter();
    }

    @Bean
    public MeasurementsRepository measurementRepository(PagingAndSortingMeasurementsRepository pagingAndSortingMeasurementsRepository, MeasurementEntityMapper measurementEntityMapper) {
        return new MeasurementsStorageAdapter(pagingAndSortingMeasurementsRepository, measurementEntityMapper);
    }

    @Bean
    public MeasurementsService measurementService(MeasurementsRepository measurementsRepository, ValuesMeasurement valuesMeasurement) {
        return new MeasurementsService(measurementsRepository, valuesMeasurement);
    }
}
