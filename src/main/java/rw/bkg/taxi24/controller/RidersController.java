package rw.bkg.taxi24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.bkg.taxi24.exceptions.HandlerNotFound;
import rw.bkg.taxi24.models.Rider;
import rw.bkg.taxi24.services.impl.RiderServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/taxi24/riders")
public class RidersController {
    final RiderServiceImpl riderService;

    public RidersController(RiderServiceImpl riderService) {
        this.riderService = riderService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getRiders() {
        return ResponseEntity.ok(riderService.getAllRiders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRider(@PathVariable String id) {
        Rider rider = riderService.getRiderById(id);
        if (rider == null) {
            throw new HandlerNotFound(String.format("Rider with id %s not found", id));
        }
        return ResponseEntity.ok(rider);
    }
    @GetMapping("/search/{lat}/{lng}")
    public ResponseEntity<?> getNearbyDrivers(@PathVariable("lat") double lat, @PathVariable("lng") double lng) {
        return ResponseEntity.ok(riderService.getClosestDrivers(new Rider(lat, lng)));
    }

}
