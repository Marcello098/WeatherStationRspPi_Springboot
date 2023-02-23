package com.example.weatherstationspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherStationSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherStationSpringApplication.class, args);
    }
}
