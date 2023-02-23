package org.example.weatherstationspring.config;

import org.example.weatherstationspring.domain.measurement.MeasurementsRepository;
import org.example.weatherstationspring.domain.measurement.MeasurementsService;
import org.example.weatherstationspring.domain.measurement.ValuesMeasurement;
import org.example.weatherstationspring.external.measurement.temporaryFakeAdapter.FakeMeasurementAdapter;
import org.example.weatherstationspring.external.storage.JpaMeasurementsRepository;
import org.example.weatherstationspring.external.storage.MeasurementEntityMapper;
import org.example.weatherstationspring.external.storage.MeasurementsStorageAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConfigurationProperties("domain.yaml")
class DomainConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
         registry.addViewController("/api/v1/measurements").setViewName("api");
    }
    
    @Bean
    public ValuesMeasurement valuesMeasurement() {
        return new FakeMeasurementAdapter();
    }

    @Bean
    public MeasurementsRepository measurementRepository(JpaMeasurementsRepository jpaMeasurementsRepository, MeasurementEntityMapper measurementEntityMapper) {
        return new MeasurementsStorageAdapter(jpaMeasurementsRepository, measurementEntityMapper);
    }

    @Bean
    public MeasurementsService measurementService(MeasurementsRepository measurementsRepository, ValuesMeasurement valuesMeasurement) {
        return new MeasurementsService(measurementsRepository, valuesMeasurement);
    }
}
