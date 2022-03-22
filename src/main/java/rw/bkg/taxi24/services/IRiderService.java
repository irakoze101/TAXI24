package rw.bkg.taxi24.services;

import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.models.Rider;

import java.util.List;

public interface IRiderService {
    List<Rider> getAllRiders();
    Rider getRiderById(int id);
    List<Driver> getClosestDrivers(Rider rider);
}
