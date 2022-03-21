package rw.bkg.taxi24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
