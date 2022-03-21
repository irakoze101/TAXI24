package services.impl;

import models.Driver;
import repos.DriversRepo;
import services.IDriverService;

import java.util.List;

public class DriverService implements IDriverService {

    final private DriversRepo driversRepo;
    public DriverService(DriversRepo driverRepository) {
        this.driversRepo = driverRepository;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driversRepo.findAll();
    }
}
