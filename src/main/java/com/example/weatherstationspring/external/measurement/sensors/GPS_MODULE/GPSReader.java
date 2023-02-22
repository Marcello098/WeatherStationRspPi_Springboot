package com.example.weatherstationspring.external.measurement.sensors.GPS_MODULE;

import com.fazecast.jSerialComm.SerialPort;

public interface GPSReader {
    void getDataFromGPSNew(SerialPort serialPort) throws InterruptedException;
    void getDataFromGPS(SerialPort serialPort);
    void stopReading();
    public SerialPort getPortNameOfGPS();
}
