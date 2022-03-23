package rw.bkg.taxi24.services.impl;

import org.springframework.stereotype.Service;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.models.Rider;
import rw.bkg.taxi24.models.Trip;
import rw.bkg.taxi24.models.TripStatus;
import rw.bkg.taxi24.repos.TripsRepo;
import rw.bkg.taxi24.services.ITripService;
import rw.bkg.taxi24.utils.Utilities;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService implements ITripService {
    final RiderServiceImpl riderService;
    final TripsRepo repo;

    public TripService(RiderServiceImpl riderService, TripsRepo repo) {
        this.riderService = riderService;
        this.repo = repo;
    }

    @Override
    public void completeTrip(String tripId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NumberFormat numberFormat = NumberFormat.getInstance();
        Trip trip = repo.findById(Long.valueOf(tripId)).get();
        Date starting = null;
        Date ending = null;
        try {
            starting = format.parse(trip.getStartTime());
            ending = format.parse(format.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        trip.setDuration(numberFormat.format((starting.getTime() - ending.getTime()) / (1000 * 60)));
        trip.setStatus(TripStatus.FINISHED);

        repo.save(trip);
    }

    @Override
    public List<Trip> getTrips(boolean isCompleted) {
        return isCompleted ? repo.findAll()
                .stream()
                .filter(t -> t.getStatus() == TripStatus.FINISHED)
                .collect(Collectors.toList()): repo.findAll();
    }

    @Override
    public Trip createTrip(Rider rider, String destination) {
        Driver driver = riderService.getClosestDrivers(rider).get(0);
        String distance = Utilities.getDistance(rider.getLatitude(),
                Double.parseDouble(destination.split(",")[0]),
                rider.getLongitude(),
                Double.parseDouble(destination.split(",")[1])) + " KM";
        Trip newTrip = new Trip(
                String.format("%s,%s", rider.getLatitude(), rider.getLongitude()),
                destination,
                distance,
                driver,
                rider,
                System.currentTimeMillis(),
                TripStatus.PENDING
        );
        repo.save(newTrip);
        return newTrip;
    }
}
