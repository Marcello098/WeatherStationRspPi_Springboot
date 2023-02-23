package org.example.weatherstationspring.external;

import org.example.weatherstationspring.external.measurement.sensors.BME280.BME280;
import org.example.weatherstationspring.external.measurement.sensors.BME280.BME280Builder;
import org.example.weatherstationspring.external.measurement.sensors.BME280.BME280Runner;
import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TemporaryMainTests {

    private static final Logger LOG = LoggerFactory.getLogger(BME280Runner.class);
    public static void main(String[] args) {
        Context pi4j = Pi4J.newAutoContext();
        try(BME280 bme280 = BME280Builder.get().context(pi4j).build()) {
            int id = bme280.getId();
            LOG.info("BME280 CHIP ID={}", id);
            int status = bme280.getStatus();
            LOG.info("BME280 status={}", status);
            BME280Runner bme280Measures = new BME280Runner(0, 0 , 0);
            for (int i=0;i<5;i++) {
                //float[] measures = BME280Runner.getBME280Data(pi4j, bme280);
                BME280Runner bme280Runner = bme280Measures.getBME280DataAsObject(pi4j, bme280);
                //bme280Measures.getBME280DataAsObject(pi4j, bme280);
                System.out.println(bme280Runner.toString());
                //System.out.println(Arrays.toString(measures));
                LocalDateTime localDT = LocalDateTime.now();
                String currentTimeStamp = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss").format(localDT);
                System.out.println(currentTimeStamp);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
