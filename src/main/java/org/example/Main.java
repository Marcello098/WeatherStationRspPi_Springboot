package org.example;

import com.fazecast.jSerialComm.SerialPort;
import com.pi4j.Pi4J;
import org.example.GPSTesting.GPSParserImpl;
import org.example.GPSTesting.GPSReader;
import org.example.GPSTesting.GPSReaderImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static String GPGGA_Test = "$GPGGA,211034,4738.9577,N,12220.9329,W,1,09,1.0,10.8,M,-18.4,M,,*42";
    static String test1 = "$GNGGA,205845.000,5147.4559,N,01924.7120,E,1,5,1.41,227.4,M,40.3,M,,*49";
    static String test2 = "$GNRMC,204526.086,V,,,,,0.11,223.91,170223,,,N*54";


    public static void main(String[] args) throws InterruptedException {
        var pi4j = Pi4J.newAutoContext();
        GPSReader gpsReader = new GPSReaderImpl();
        // Implementation v1
        SerialPort port = gpsReader.prepareForFetchingData();
        if (port != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(port.getInputStream()));
            StringBuilder line = new StringBuilder();
            while (true) {
                String fetchedData = gpsReader.fetchData(port, br, line);
                System.out.println(fetchedData);
            }
        }

        // Implementation v2
       // SerialPort portNameOfGPS  = gpsReader.getPortNameOfGPS();
        //gpsReader.getDataFromGPSNew(portNameOfGPS);
        //String[] coordstest = {"5147.4532", "N", "01924.7052", "W"};
        //GPSParserImpl gpsParserImpl = new GPSParserImpl(51.33, 19.33, "17z");
        //gpsParserImpl.parseCoordinatesForGMaps(coordstest);
        // SerialPort serialPortOfGPS = gpsCoordinatesParser.getPortNameOfGPS();
        // gpsCoordinatesParser.getDataFromGPS2(serialPortOfGPS);
        // SerialGPSReader serialGPSReader = new SerialGPSReader();
        //erialGPSReader.executeGPSReader(pi4j);
       // String[] strings = "$GNRMC,222832.000,A,5147.4532,N,01924.7052,E,1.11,303.66,170223,,,A*76".split(",", 8);
       // for (String string : strings) {
       //     System.out.println(string);
       // }

        
    }
}


/*
$GNRMC,204526.086,V,,,,,0.11,223.91,170223,,,N*54
$GNVTG,223.91,T,,M,0.11,N,0.20,K,N*25
$GNGGA,204526.086,,,,,0,0,,,M,,M,,*5F
$GPGSA,A,1,,,,,,,,,,,,,,,*1E
$BDGSA,A,1,,,,,,,,,,,,,,,*0F
$GPGSV,3,1,09,08,68,220,,21,54,287,,10,52,061,29,27,44,166,18*7C
$GPGSV,3,2,09,32,39,121,,01,26,281,,23,18,055,19,14,15,323,*75
$GPGSV,3,3,09,24,05,043,*44
$BDGSV,1,1,00*68
$GNGLL,,,,,204526.086,V,N*6D
$GNRMC,204527.086,V,,,,,0.03,224.45,170223,,,N*58
$GNVTG,224.45,T,,M,0.03,N,0.05,K,N*2F
$GNGGA,204527.086,,,,,0,0,,,M,,M,,*5E
$GPGSA,A,1,,,,,,,,,,,,,,,*1E
$BDGSA,A,1,,,,,,,,,,,,,,,*0F
$GPGSV,3,1,09,08,68,220,,21,54,287,,10,52,061,29,27,44,166,18*7C
$GPGSV,3,2,09,32,39,121,,01,26,281,,23,18,055,20,14,15,323,*7F
$GPGSV,3,3,09,24,05,043,*44
$BDGSV,1,1,00*68
$GNGLL,,,,,204527.086,V,N*6C
 */

/*
String[] vowels = {"$GNRMC,204526.086,V,,,,,0.11,223.91,170223,,,N*54","$GNRMC,204527.086,V,,,,,0.03,224.45,170223,,,N*58","i","o","u"};
        List<String> vowelsList = Arrays.asList(vowels);
        NMEA nmea = new NMEA();
        nmea.parse(test1);
        System.out.println(nmea.position);
 */