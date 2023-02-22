package org.example.GPSTesting;

public interface GPSParser {

    String[] extractCoordinatesFromLine (StringBuilder inputLine);
    String parseSingleCoordinate(String[] coordinatesWSide);
    GPSParserImpl parseCoordinatesForGMaps(String[] coordinates);






}
