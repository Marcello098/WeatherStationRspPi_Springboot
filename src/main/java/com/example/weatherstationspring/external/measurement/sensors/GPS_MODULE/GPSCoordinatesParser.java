package com.example.weatherstationspring.external.measurement.sensors.GPS_MODULE;

import com.fazecast.jSerialComm.SerialPort;

public class GPSCoordinatesParser {

    public void getDataFromGPS() {
        SerialPort[] ports = SerialPort.getCommPorts();
        
        for (SerialPort port: ports) {
            System.out.println(port.getSystemPortName());
        }
    }
}
