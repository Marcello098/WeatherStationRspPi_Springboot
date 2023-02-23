package org.example.GPSTesting;

import com.fazecast.jSerialComm.SerialPort;

public class GPSRunner {

    public void runGPS() throws InterruptedException {
        GPSReader gpsReader = new GPSReaderImpl();
        GPSParser gpsParser = new GPSParserImpl(0.0,0.0,"18");
        SerialPort serialPort = gpsReader.getPortNameOfGPS();
        gpsReader.getDataFromGPSNew(serialPort);
        String[] coordinates = gpsParser.extractCoordinatesFromLine(null);
        gpsParser.parseCoordinatesForGMaps(coordinates);

    }
}
