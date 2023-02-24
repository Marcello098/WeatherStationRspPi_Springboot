package org.example.weatherstationspring;


/*import org.example.DataContainer;
import org.example.GUI;*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.swing.*;
import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class WeatherStationSpringApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(WeatherStationSpringApplication.class, args);
        //GUI weatherRspPiGUI = new GUI(new DataContainer());
    }
}
