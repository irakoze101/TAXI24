package rw.bkg.taxi24.services.impl;

import org.springframework.stereotype.Service;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.models.Rider;
import rw.bkg.taxi24.repos.RidersRepo;
import rw.bkg.taxi24.services.IRiderService;

import java.util.List;

@Service
public class RiderServiceImpl implements IRiderService {
    final RidersRepo repo;

    public RiderServiceImpl(RidersRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Rider> getAllRiders() {
        return repo.findAll();
    }

    @Override
    public Rider getRiderById(int id) {
        return null;
    }

    @Override
    public List<Driver> getClosestDrivers(Rider rider) {
        return null;
    }
}
