package org.example.weatherstationspring.external.measurement.sensors.BME280;

import com.pi4j.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BME280Runner {
    private float temperature, pressure, relativeHumidity;
    private static final Logger LOG = LoggerFactory.getLogger(BME280Runner.class);
    static LocalDateTime localDT = LocalDateTime.now();
    static String currentTimeStamp = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss").format(localDT);

    public BME280Runner(float temperature, float pressure, float relativeHumidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.relativeHumidity = relativeHumidity;
    }

    @Override
    public String toString() {
        return "Temperature: " + this.temperature + "[C]; " +
                "Pressure: " + this.pressure + "[hPa]; " +
                "RelativeHumidity: " + this.relativeHumidity + "[%]";
    }

    public BME280Runner getBME280DataAsObject(Context context, BME280 bme280) {
        float temperatureRead, pressureRead, relativeHumidityRead;
        BME280Impl.Data data = bme280.getSensorValues();
        temperatureRead = data.getTemperature();
        pressureRead = data.getPressure();
        relativeHumidityRead = data.getRelativeHumidity();          ;
        LOG.info("Temperature: {} C, Pressure: {} hPa, Relative humidity: {} %",
                String.format("%.2f", temperatureRead), String.format("%.4f",  pressureRead),
                String.format("%.3f", relativeHumidityRead));
        return new BME280Runner(temperatureRead, pressureRead, relativeHumidityRead);
    }

    public static float[] getBME280Data(Context context, BME280 bme280) {
        float temperature, pressure, relativeHumidity;
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