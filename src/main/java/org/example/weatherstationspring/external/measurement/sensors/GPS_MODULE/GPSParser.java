package org.example.weatherstationspring.external.measurement.sensors.GPS_MODULE;

public interface GPSParser {

    String[] extractCoordinatesFromLine (StringBuilder inputLine);
    String parseSingleCoordinate(String[] coordinatesWSide);
    GPSParserImpl parseCoordinatesForGMaps(String[] coordinates);






}
