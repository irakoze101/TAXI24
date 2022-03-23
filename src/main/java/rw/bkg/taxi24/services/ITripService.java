package rw.bkg.taxi24.services;

import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.models.Rider;
import rw.bkg.taxi24.models.Trip;

import java.util.List;

public interface ITripService {
    void completeTrip(Trip trip);
    List<Trip> getTrips(boolean isCompleted);
    Trip createTrip(Rider rider, String destination);
    Trip getTrip(String tripId);
}
