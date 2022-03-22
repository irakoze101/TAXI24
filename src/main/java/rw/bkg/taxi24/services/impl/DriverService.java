package rw.bkg.taxi24.services.impl;

import org.springframework.stereotype.Service;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.repos.DriversRepo;
import rw.bkg.taxi24.services.IDriverService;

import java.util.List;

@Service
public class DriverService implements IDriverService {

    final private DriversRepo driversRepo;
    public DriverService(DriversRepo driverRepository) {
        this.driversRepo = driverRepository;
    }

    @Override
    public Driver getDriver(String licenseNumber) {
        if (!driversRepo.existsById(licenseNumber)){
            return null;
        } else {
            return driversRepo.findById(licenseNumber).get();
        }
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driversRepo.findAll();
    }

    @Override
    public List<Driver> getNearbyDrivers(double latitude, double longitude) {
        return getAllDrivers()
                .stream()
                .filter(d -> getDistance(latitude, d.getLatitude(), longitude, d.getLongitude()) <= 3)
                .collect(java.util.stream.Collectors.toList());
    }

    public double getDistance(double latitudeOne, double latitudeTwo, double longitudeOne,
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