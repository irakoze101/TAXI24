package rw.bkg.taxi24.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.repos.DriversRepo;

@ContextConfiguration(classes = {DriverService.class})
@ExtendWith(SpringExtension.class)
class DriverServiceTest {
    @Autowired
    private DriverService driverService;

    @MockBean
    private DriversRepo driversRepo;

    @Test
    void shouldGetAllDrivers() {
        Driver driver = new Driver();
        driver.setLatitude(-1.970579);
        driver.setLicenseId("42");
        driver.setLongitude(30.104429);
        driver.setName("Name");
        driver.setTrips(new ArrayList<>());
        Driver drive = new Driver();
        driver.setLatitude(-1.970579);
        driver.setLicenseId("42");
        driver.setLongitude(30.104429);
        driver.setName("Name");
        driver.setTrips(new ArrayList<>());
        Driver driv = new Driver();
        driver.setLatitude(-1.970579);
        driver.setLicenseId("42");
        driver.setLongitude(30.104429);
        driver.setName("Name");
        driver.setTrips(new ArrayList<>());
        Driver dri = new Driver();
        driver.setLatitude(-1.947360965940385);
        driver.setLicenseId("42");
        driver.setLongitude(30.06600480252696);
        driver.setName("Nearest Driver");
        driver.setTrips(new ArrayList<>());
        ArrayList<Driver> driverList = new ArrayList<>();

        driverList.add(driver);
        driverList.add(drive);
        driverList.add(driv);
        driverList.add(dri);

        when(this.driversRepo.findAll()).thenReturn(driverList);
        assertEquals(4, this.driverService.getAllDrivers().size());
        verify(this.driversRepo).findAll();
    }

    @Test
    void testGetNearbyDrivers() {
        when(this.driversRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.driverService.getNearbyDrivers(10.0d, 10.0d).isEmpty());
        verify(this.driversRepo).findAll();
    }

    @Test
    void testGetNearbyDrivers2() {
        Driver driver = new Driver();
        driver.setLatitude(-1.970579);
        driver.setLicenseId("42");
        driver.setLongitude(30.104429);
        driver.setName("Name");
        driver.setTrips(new ArrayList<>());
        Driver drive = new Driver();
        driver.setLatitude(-1.970579);
        driver.setLicenseId("42");
        driver.setLongitude(30.104429);
        driver.setName("Name");
        driver.setTrips(new ArrayList<>());
        Driver driv = new Driver();
        driver.setLatitude(-1.970579);
        driver.setLicenseId("42");
        driver.setLongitude(30.104429);
        driver.setName("Name");
        driver.setTrips(new ArrayList<>());
        Driver dri = new Driver();
        driver.setLatitude(-1.9472751848213399);
        driver.setLicenseId("42");
        driver.setLongitude(30.06617646389006);
        driver.setName("Nearest Driver");
        driver.setTrips(new ArrayList<>());

        ArrayList<Driver> driverList = new ArrayList<>();
        driverList.add(driver);
        when(this.driversRepo.findAll()).thenReturn(driverList);
        assertEquals(1, this.driverService.getNearbyDrivers(-1.943189871008775, 30.058778098405273).size());
        verify(this.driversRepo).findAll();
    }

    @Test
    void testGetNearbyDriver() {
        Driver driver = new Driver();
        driver.setLatitude(-1.970579);
        driver.setLicenseId("42");
        driver.setLongitude(30.104429);
        driver.setName("Name");
        driver.setTrips(new ArrayList<>());
        Driver drive = new Driver();
        driver.setLatitude(-1.970579);
        driver.setLicenseId("42");
        driver.setLongitude(30.104429);
        driver.setName("Name");
        driver.setTrips(new ArrayList<>());
        Driver driv = new Driver();
        driver.setLatitude(-1.970579);
        driver.setLicenseId("42");
        driver.setLongitude(30.104429);
        driver.setName("Name");
        driver.setTrips(new ArrayList<>());
        Driver dri = new Driver();
        driver.setLatitude(-1.947360965940385);
        driver.setLicenseId("42");
        driver.setLongitude(30.06600480252696);
        driver.setName("Nearest Driver");
        driver.setTrips(new ArrayList<>());
        ArrayList<Driver> driverList = new ArrayList<>();

        driverList.add(driver);
        driverList.add(drive);
        driverList.add(driv);
        driverList.add(dri);

        when(this.driversRepo.findAll()).thenReturn(driverList);
        assertEquals("Nearest Driver", this.driverService.getNearbyDrivers(-1.9474038564982663, 30.065876056504635).get(0).getName());
        verify(this.driversRepo).findAll();
    }

    @Test
    void testGetDistance() {
        assertEquals(176, this.driverService.getDistance(-1.970579, -3.361378, 30.104429, 29.359879));
    }
}

