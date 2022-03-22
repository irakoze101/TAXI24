package rw.bkg.taxi24.services;

import org.springframework.stereotype.Component;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.models.Rider;

import java.util.List;

@Component
public interface IRiderService {
    List<Rider> getAllRiders();
    Rider getRiderById(int id);
    List<Driver> getClosestDrivers(Rider rider);
}
