package rw.bkg.taxi24.services.impl;

import org.springframework.stereotype.Service;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.repos.DriversRepo;
import rw.bkg.taxi24.services.IDriverService;
import rw.bkg.taxi24.utils.Utilities;

import java.util.List;

@Service
public class DriverService implements IDriverService {

    final private DriversRepo driversRepo;
    public DriverService(DriversRepo driverRepository) {
        this.driversRepo = driverRepository;
    }

    @Override
    public Driver getDriver(String licenseNumber) {
        if (driversRepo.findById(licenseNumber).isEmpty()){
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
    public List<Driver> getNearbyDrivers(double latitude, double longitude, int km) {
        return getAllDrivers()
                .stream()
                .filter(d -> Utilities.getDistance(latitude, d.getLatitude(), longitude, d.getLongitude()) <= km)
                .collect(java.util.stream.Collectors.toList());
    }
}