package rw.bkg.taxi24.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.models.Rider;
import rw.bkg.taxi24.services.impl.RiderServiceImpl;

@ContextConfiguration(classes = {RidersController.class})
@ExtendWith(SpringExtension.class)
class RidersControllerTest {
    @MockBean
    private RiderServiceImpl riderServiceImpl;

    @Autowired
    private RidersController ridersController;

    @Test
    void testGetRider() throws Exception {
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
        when(this.riderServiceImpl.getRiderById((String) any())).thenReturn(rider);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/taxi24/riders/{id}", "42");
        MockMvcBuilders.standaloneSetup(this.ridersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"name\":\"Name\",\"surname\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"phoneNumber"
                                        + "\":\"4105551212\",\"address\":\"42 Main St\",\"city\":\"Oxford\",\"country\":\"GB\",\"postalCode\":\"Postal Code\","
                                        + "\"birthDate\":\"2020-03-01\",\"latitude\":10.0,\"longitude\":10.0}"));
    }

    @Test
    void testGetRider2() throws Exception {
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
        when(this.riderServiceImpl.getRiderById((String) any())).thenReturn(rider);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/taxi24/riders/{id}", "42");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.ridersController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"name\":\"Name\",\"surname\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"phoneNumber"
                                        + "\":\"4105551212\",\"address\":\"42 Main St\",\"city\":\"Oxford\",\"country\":\"GB\",\"postalCode\":\"Postal Code\","
                                        + "\"birthDate\":\"2020-03-01\",\"latitude\":10.0,\"longitude\":10.0}"));
    }

    @Test
    void givenNotFound_whenGetSpecificException_thenNotFoundCode() throws Exception {
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
        when(this.riderServiceImpl.getRiderById((String) any())).thenReturn(null);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/taxi24/riders/{id}", "42");
        assertThatThrownBy(() -> {
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/taxi24/riders/{id}", "42");
            MockMvcBuilders.standaloneSetup(this.ridersController)
                    .build()
                    .perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isNotFound());
        })
                .isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(RuntimeException.class)
                .hasMessageContaining("Rider with id ");
    }
}

