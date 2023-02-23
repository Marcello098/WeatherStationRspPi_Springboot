package org.example.GPSTesting;

import com.fazecast.jSerialComm.SerialPort;

import java.io.BufferedReader;

public interface GPSReader {
    void getDataFromGPSNew(SerialPort serialPort) throws InterruptedException;
    void getDataFromGPS(SerialPort serialPort);
    void stopReading();
    public SerialPort getPortNameOfGPS();

    String fetchData(SerialPort serialPort, BufferedReader br, StringBuilder line);

    public String fetchData2 (SerialPort serialPort);

    SerialPort prepareForFetchingData() throws InterruptedException;

    void getDataFromGPSNew2(SerialPort serialPort) throws InterruptedException;
}
