package rw.bkg.taxi24.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.models.Rider;
import rw.bkg.taxi24.repos.RidersRepo;
import rw.bkg.taxi24.services.IRiderService;
import rw.bkg.taxi24.utils.Utilities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RiderServiceImpl implements IRiderService {
    final RidersRepo repo;
    final DriverService driversService;
    protected final Logger Log = LoggerFactory.getLogger(RiderServiceImpl.class);

    public RiderServiceImpl(RidersRepo repo, DriverService driversService) {
        this.repo = repo;
        this.driversService = driversService;
    }

    @Override
    public List<Rider> getAllRiders() {
        return repo.findAll();
    }

    @Override
    public Rider getRiderById(String id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Driver> getClosestDrivers(Rider rider) {
        return driversService.getAllDrivers()
                .stream()
                /*.sorted((d1, d2) -> Double.compare(
                        Utilities.getDistance(rider.getLatitude(), d1.getLatitude(), rider.getLongitude(), d1.getLongitude()),
                        Utilities.getDistance(rider.getLatitude(), d2.getLatitude(), rider.getLongitude(), d2.getLongitude())
                ))*/
                .sorted(Comparator.comparingDouble(d -> Utilities.getDistance(rider.getLatitude(),
                        d.getLatitude(), rider.getLongitude(), d.getLongitude())))
                .limit(3)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
