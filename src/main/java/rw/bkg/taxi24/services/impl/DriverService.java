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
    public List<Driver> getAllDrivers() {
        return driversRepo.findAll();
    }
}