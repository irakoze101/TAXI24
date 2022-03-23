package rw.bkg.taxi24.utils;

public class Utilities {
    public static double getDistance(double latitudeOne, double latitudeTwo, double longitudeOne,
                                     double longitudeTwo) {
        final int R = 6371;
        double latDistance = Math.toRadians(latitudeTwo - latitudeOne);
        double lonDistance = Math.toRadians(longitudeTwo - longitudeOne);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitudeOne)) * Math.cos(Math.toRadians(latitudeTwo))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;
        distance = Math.pow(distance, 2);
        double distanceInMeters = Math.sqrt(distance);

        return Math.ceil(distanceInMeters/1000);
    }
}
