package rw.bkg.taxi24.services.impl;

import org.springframework.stereotype.Service;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.models.Rider;
import rw.bkg.taxi24.models.Trip;
import rw.bkg.taxi24.services.ITripService;
import rw.bkg.taxi24.utils.Utilities;

import java.util.List;

@Service
public class TripService implements ITripService {
    final RiderServiceImpl riderService;

    public TripService(RiderServiceImpl riderService) {
        this.riderService = riderService;
    }

    @Override
    public void completeTrip(String tripId) {

    }

    @Override
    public List<Trip> getTrips() {
        return null;
    }

    @Override
    public Trip createTrip(Rider rider, String destination) {
        Driver driver = riderService.getClosestDrivers(rider).get(0);
        String distance = Utilities.getDistance(rider.getLatitude(),
                Double.parseDouble(destination.split(",")[0]),
                rider.getLongitude(),
                Double.parseDouble(destination.split(",")[1])) + " KM";
        return new Trip(
                String.format("%s,%s", rider.getLatitude(), rider.getLongitude()),
                destination,
                distance,
                driver,
                rider
        );
    }
}
