package com.example.weatherstationspring.external.measurement.sensors.BME280;


import com.example.weatherstationspring.external.measurement.helpers.I2CDevice;

// Child interface of I2CDevice as BME280 is a member of I2CDevice family
public interface BME280 extends I2CDevice {
    /**
     * Expected to return 0x60, the value is fixed and can be used to check whether communication is functioning.
     * @return chip Id.
     */
    int getId();

    /**
     * Reset sensor.
     */
    void reset();

    /**
     * Get sensor status.
     * @return - sensor status.
     */
    int getStatus();

    /**
     * Get ambient temperature reading [Celsius].
     * @return - temperature reading [Celsius].
     */
    float getTemperature();

    /**
     * Get ambient pressure reading [Pascal].
     * @return - pressure reading [Pascal].
     */
    float getPressure();

    /**
     * Get ambient relative humidity reading [%].
     * @return - relative humidity reading [%].
     */
    float getRelativeHumidity();

    /**
     * Read all sensors (ambient temperature, pressure and relative humidity).
     * @return - ambient temperature, pressure and relative humidity data.
     */
    BME280Impl.Data getSensorValues();

}
