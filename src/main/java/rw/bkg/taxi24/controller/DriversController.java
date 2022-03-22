package rw.bkg.taxi24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.bkg.taxi24.exceptions.HandlerNotFound;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.services.impl.DriverService;

@CrossOrigin
@RestController
public class DriversController {
    final private DriverService driversService;

    public DriversController(DriverService driversService) {
        this.driversService = driversService;
    }

    @GetMapping("/drivers")
    public ResponseEntity<?> getDrivers() {
        return ResponseEntity.ok(driversService.getAllDrivers());
    }

    @GetMapping("/drivers/get/{licenseNumber}")
    public ResponseEntity<?> getDriver(@PathVariable("licenseNumber") String licenseNumber) {
        Driver driver = driversService.getDriver(licenseNumber);
        if (driver == null) {
            throw new HandlerNotFound(String.format("Driver with license number %s not found", licenseNumber));
        }
        return ResponseEntity.ok(driver);
    }
}
