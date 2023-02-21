package com.example.weatherstationspring.external.measurement.sensors.BME280;

import com.pi4j.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BME280Test {
    private float temperature, pressure, relativeHumidity;
    private static final Logger LOG = LoggerFactory.getLogger(BME280Test.class);
    static LocalDateTime localDT = LocalDateTime.now();
    static String currentTimeStamp = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss").format(localDT);

    public BME280Test(float temperature, float pressure, float relativeHumidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.relativeHumidity = relativeHumidity;
    }

    public static void test(Context context) throws Exception {
        LOG.info("<---STARTING BME280Test--->");
        try (BME280 bme280 = BME280Builder.get().context(context).build()) {
            int id = bme280.getId();
            LOG.info("BME280 CHIP ID={}", id);
            int status = bme280.getStatus();
            LOG.info("BME280 status={}", status);
            for (int i = 0; i < 3; i++) {
                BME280Impl.Data data = bme280.getSensorValues();
               // float pressure = bme280.getPressure() / 1000;
               // float temperature = bme280.getTemperature();
               // float relativeHumility = bme280.getRelativeHumidity();
               // float pres = data.getPressure() / 1000;
                LOG.info("[{}] Temperature: {} C, Pressure: {} hPa, Relative humidity: {} %", i,
                        String.format("%.3f", data.getTemperature()), String.format("%.3f", data.getPressure()), String.format("%.3f", data.getRelativeHumidity()));
                System.out.println(data.getTemperature());
                System.out.println(data.getPressure());
                System.out.println(data.getRelativeHumidity());
                System.out.println(currentTimeStamp);
                Thread.sleep(1000);
            }
        }
        LOG.info("<---BME280Test DONE!--->");
    }

    public BME280Test getBME280DataAsObject(Context context, BME280 bme280) {
        float temperatureRead = 0, pressureRead = 0, relativeHumidityRead = 0;
        BME280Impl.Data data = bme280.getSensorValues();
        temperatureRead = data.getTemperature();
        pressureRead = data.getPressure();
        relativeHumidityRead = data.getRelativeHumidity();          ;
        LOG.info("Temperature: {} C, Pressure: {} hPa, Relative humidity: {} %",
                String.format("%.2f", temperatureRead), String.format("%.4f",  pressureRead),
                String.format("%.3f", relativeHumidityRead));
        return new BME280Test(temperatureRead, pressureRead, relativeHumidityRead);
    }

    public static float[] getBME280Data(Context context, BME280 bme280) {
        float temperature = 0, pressure = 0, relativeHumidity = 0;
        float[] returnedValues = new float[3];
        BME280Impl.Data data = bme280.getSensorValues();
        temperature = data.getTemperature();
        pressure = data.getPressure() / 1000;
        relativeHumidity = data.getRelativeHumidity();
        LOG.info("Temperature: {} C, Pressure: {} hPa, Relative humidity: {} %",
                String.format("%.2f", temperature), String.format("%.4f", pressure), String.format("%.3f", relativeHumidity));

        returnedValues[0] = temperature;
        returnedValues[1] = pressure;
        returnedValues[2] = relativeHumidity;
        return returnedValues;
    }
}