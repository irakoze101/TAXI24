package rw.bkg.taxi24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.bkg.taxi24.exceptions.HandlerNotFound;
import rw.bkg.taxi24.models.Rider;
import rw.bkg.taxi24.models.Trip;
import rw.bkg.taxi24.models.TripStatus;
import rw.bkg.taxi24.services.impl.RiderServiceImpl;
import rw.bkg.taxi24.services.impl.TripService;

@CrossOrigin
@RestController
@RequestMapping("/taxi24/trips")
public class TripsController {
    final TripService service;
    final RiderServiceImpl riderService;

    public TripsController(TripService service, RiderServiceImpl riderService) {
        this.service = service;
        this.riderService = riderService;
    }

    @GetMapping("/create")
    public ResponseEntity<?> createTrip(@RequestParam(value = "start_lat", required = true) String startLat,
                                        @RequestParam(value = "start_lng", required = true) String startLng,
                                        @RequestParam(value = "end_lat", required = true) String endLat,
                                        @RequestParam(value = "end_lng", required = true) String endLng,
                                        @RequestParam(value = "passenger_id", required = true) String passengerId) {
        Rider rider = riderService.getRiderById(passengerId);
        if (rider == null) {
            return ResponseEntity.badRequest().body("Rider not found");
        }
        rider.setLatitude(Double.parseDouble(startLat));
        rider.setLongitude(Double.parseDouble(startLng));

        Trip trip = service.createTrip(rider, String.format("%s,%s", endLat, endLng));
        return ResponseEntity.ok(trip);
    }

    @GetMapping("/complete")
    public ResponseEntity<?> completeTrip(@RequestParam(value = "trip_id", required = true) String tripId) {
        Trip trip = service.getTrip(tripId);
        if (trip == null) {
            return ResponseEntity.badRequest().body("The trip does not exist");
        }
        service.completeTrip(trip);
        trip.setStatus(TripStatus.FINISHED);
        return ResponseEntity.ok(trip);
    }

    @GetMapping("/list/{status}")
    public ResponseEntity<?> getTripsByStatus(@PathVariable("status") TripStatus status) {
        return ResponseEntity.ok(service.getTrips(status.equals(TripStatus.PENDING)));
    }
}
