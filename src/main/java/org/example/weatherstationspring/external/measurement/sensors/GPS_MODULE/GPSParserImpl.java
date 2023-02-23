package org.example.weatherstationspring.external.measurement.sensors.GPS_MODULE;

import java.util.Objects;

public class GPSParserImpl implements GPSParser {

    String properLineStart = "$GNRMC";
    String properLineStart2 = "$GNGGA";
    Double gpsLatitude;
    Double gpsLongitude;
    String googleMapsZoom;

    public GPSParserImpl(Double gpsLatitude, Double gpsLongitude, String googleMapsZoom) {
        this.gpsLatitude = gpsLatitude;
        this.gpsLongitude = gpsLongitude;
        this.googleMapsZoom = googleMapsZoom;
    }

    @Override
    public String[] extractCoordinatesFromLine(StringBuilder inputLine) {
        String extractedLatitude;
        String extractedLongitude;
        String[] gpsValues;
        String[] coordinates = new String[4];
        String fetchedCoordinatesLine = inputLine.toString();

        if (fetchedCoordinatesLine.startsWith(properLineStart)) {
            gpsValues = fetchedCoordinatesLine.split(",", 8);
            extractedLatitude = gpsValues[3];
            extractedLongitude = gpsValues[5];

            coordinates[0] = extractedLatitude;
            coordinates[1] = gpsValues[4];
            coordinates[2] = extractedLongitude;
            coordinates[3] = gpsValues[6];

            return coordinates;
        }
        if (fetchedCoordinatesLine.startsWith(properLineStart2)) {
            gpsValues = fetchedCoordinatesLine.split(",", 7);
            extractedLatitude = gpsValues[2];
            extractedLongitude = gpsValues[4];
            
            coordinates[0] = extractedLatitude;
            coordinates[1] = gpsValues[3];
            coordinates[2] = extractedLongitude;
            coordinates[3] = gpsValues[5];

            return coordinates;
        }
        return coordinates;
    }

    
    @Override
    public String parseSingleCoordinate(String[] coordinatesWSide) {
        String coordinate = coordinatesWSide[0];
        String side = coordinatesWSide[1];
        String coordinateDs;
        String coordinateMs;
        String resultCoordinate;

        if (coordinate.startsWith("0")) {
            coordinate = coordinate.substring(1);
        }

        coordinateDs = coordinate.substring(0,2);
        coordinateMs = coordinate.substring(2);
        double tempCoordinateMs = Double.parseDouble(coordinateMs)/60;
        coordinateMs = Double.toString(tempCoordinateMs);
        coordinateMs = coordinateMs.substring(1,7);
        resultCoordinate = coordinateDs + coordinateMs;
        if (Objects.equals(side, "S") || Objects.equals(side, "W")) {
            resultCoordinate = "-" + resultCoordinate;
        }
        return resultCoordinate;
    }
    @Override
    public GPSParserImpl parseCoordinatesForGMaps(String[] coordinates) {

        String latitudeResult;
        String longitudeResult;
        String[] latitudeInfos = {coordinates[0], coordinates[1]};
        String[] longitudeInfos = {coordinates[2], coordinates[3]};

        latitudeResult = parseSingleCoordinate(latitudeInfos);
        longitudeResult = parseSingleCoordinate(longitudeInfos);
        Double latitudeRes = Double.parseDouble(latitudeResult);
        Double longitudeRes = Double.parseDouble(latitudeResult);
        System.out.println(latitudeResult);
        System.out.println(longitudeResult);

        return new GPSParserImpl(latitudeRes, longitudeRes, "17z");
    }


}



// LENGTH POWINIEN BYĆ FIXED, ALE JESLI NIE TO DOPELNIJ ZERAMI STRING UTILS RIGHT PAD