package rw.bkg.taxi24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.bkg.taxi24.exceptions.HandlerBadRequest;
import rw.bkg.taxi24.exceptions.HandlerNotFound;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.models.Rider;
import rw.bkg.taxi24.services.IDriverService;
import rw.bkg.taxi24.services.impl.DriverService;

@CrossOrigin
@RestController
@RequestMapping("/taxi24/drivers")
public class DriversController {
    final private IDriverService driversService;

    public DriversController(IDriverService driversService) {
        this.driversService = driversService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getDrivers() {
        return ResponseEntity.ok(driversService.getAllDrivers());
    }

    @GetMapping("/get/{licenseNumber}")
    public ResponseEntity<?> getDriver(@PathVariable("licenseNumber") String licenseNumber) {
        Driver driver = driversService.getDriver(licenseNumber);
        if (driver == null) {
            throw new HandlerNotFound(String.format("Driver with license number %s not found", licenseNumber));
        }
        return ResponseEntity.ok(driver);
    }

    @PostMapping("/search")
    public ResponseEntity<?> getNearbyDrivers(@RequestParam("la") double latitude, @RequestParam("lo") double longitude) {
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180 ||
                (latitude == 0 && longitude == 0)) {
            throw new HandlerBadRequest("Invalid latitude or longitude");
        }
        return ResponseEntity.ok(driversService.getNearbyDrivers(latitude, longitude, 3));
    }
}
