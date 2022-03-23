package rw.bkg.taxi24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.bkg.taxi24.services.impl.RiderServiceImpl;
import rw.bkg.taxi24.services.impl.TripService;

@RestController
public class TripsController {
    final TripService service;
    final RiderServiceImpl riderService;

    public TripsController(TripService service, RiderServiceImpl riderService) {
        this.service = service;
        this.riderService = riderService;
    }

    public ResponseEntity<?> createTrip(@RequestParam(value = "start_lat", required = true) double startLat,
                                        @RequestParam(value = "start_lng", required = true) double startLng,
                                        @RequestParam(value = "end_lat", required = true) double endLat,
                                        @RequestParam(value = "end_lng", required = true) double endLng,
                                        @RequestParam(value = "passenger_id", required = true) String passengerId) {
        if (riderService.getRiderById(passengerId) == null) {
            return ResponseEntity.badRequest().body("Rider with id " + passengerId + " does not exist");
        }
        return null;
    }
}
