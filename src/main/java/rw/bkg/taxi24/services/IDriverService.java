package rw.bkg.taxi24.services;

import rw.bkg.taxi24.models.Driver;

import java.util.List;

public interface IDriverService {
    Driver getDriver(String licenseNumber);
    List<Driver> getAllDrivers();
    List<Driver> getNearbyDrivers(double latitude, double longitude);
}
