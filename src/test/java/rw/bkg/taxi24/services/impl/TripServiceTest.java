package rw.bkg.taxi24.services.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.models.Rider;

@ContextConfiguration(classes = {TripService.class})
@ExtendWith(SpringExtension.class)
class TripServiceTest {
    @MockBean
    private RiderServiceImpl riderServiceImpl;

    @Autowired
    private TripService tripService;

    @Test
    @Disabled("TODO: This test is incomplete")
    void testCreateTrip() {

        when(this.riderServiceImpl.getClosestDrivers((Rider) any())).thenReturn(new ArrayList<>());

        Rider rider = new Rider();
        rider.setAddress("42 Main St");
        rider.setBirthDate("2020-03-01");
        rider.setCity("Oxford");
        rider.setCountry("GB");
        rider.setEmail("jane.doe@example.org");
        rider.setId("42");
        rider.setLatitude(-1.9522675995376877);
        rider.setLongitude(30.063625530228894);
        rider.setName("Name");
        rider.setPassword("iloveyou");
        rider.setPhoneNumber("4105551212");
        rider.setPostalCode("Postal Code");
        rider.setSurname("Doe");
        rider.setTrips(new ArrayList<>());
        this.tripService.createTrip(rider, "-1.9683483900219163, 30.160829957671442");
    }

    @Test
    @Disabled("TODO: This test is incomplete")
    void testCreateTrip2() {
        // TODO: This test is incomplete.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NumberFormatException: For input string: "Destination"
        //       at jdk.internal.math.FloatingDecimal.readJavaFormatString(FloatingDecimal.java:2054)
        //       at jdk.internal.math.FloatingDecimal.parseDouble(FloatingDecimal.java:110)
        //       at java.lang.Double.parseDouble(Double.java:543)
        //       at rw.bkg.taxi24.services.impl.TripService.createTrip(TripService.java:34)
        //   In order to prevent createTrip(Rider, String)
        //   from throwing NumberFormatException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createTrip(Rider, String).
        //   See https://diff.blue/R013 to resolve this issue.

        Driver driver = new Driver();
        driver.setLatitude(10.0d);
        driver.setLicenseId("42");
        driver.setLongitude(10.0d);
        driver.setName("Name");
        driver.setTrips(new ArrayList<>());

        ArrayList<Driver> driverList = new ArrayList<>();
        driverList.add(driver);
        when(this.riderServiceImpl.getClosestDrivers((Rider) any())).thenReturn(driverList);

        Rider rider = new Rider();
        rider.setAddress("42 Main St");
        rider.setBirthDate("2020-03-01");
        rider.setCity("Oxford");
        rider.setCountry("GB");
        rider.setEmail("jane.doe@example.org");
        rider.setId("42");
        rider.setLatitude(10.0d);
        rider.setLongitude(10.0d);
        rider.setName("Name");
        rider.setPassword("iloveyou");
        rider.setPhoneNumber("4105551212");
        rider.setPostalCode("Postal Code");
        rider.setSurname("Doe");
        rider.setTrips(new ArrayList<>());
        this.tripService.createTrip(rider, "Destination");
    }
}

