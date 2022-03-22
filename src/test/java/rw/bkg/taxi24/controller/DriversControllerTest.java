package rw.bkg.taxi24.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;
import rw.bkg.taxi24.models.Driver;
import rw.bkg.taxi24.services.impl.DriverService;

@ContextConfiguration(classes = {DriversController.class})
@ExtendWith(SpringExtension.class)
class DriversControllerTest {
    @MockBean
    private DriverService driverService;

    @Autowired
    private DriversController driversController;

    @Test
    void testGetDriver() throws Exception {
        Driver driver = new Driver();
        driver.setLatitude(10.0d);
        driver.setLicenseId("42");
        driver.setLongitude(10.0d);
        driver.setName("Name");
        driver.setTrips(new ArrayList<>());
        when(this.driverService.getDriver((String) any())).thenReturn(driver);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/drivers/get/{licenseNumber}", "42");
        MockMvcBuilders.standaloneSetup(this.driversController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"licenseId\":\"42\",\"longitude\":10.0,\"latitude\":10.0,\"name\":\"Name\"}"));
    }

    @Test
    void givenNotFound_whenGetSpecificException_thenNotFoundCode() throws Exception {
        Driver driver = new Driver();
        driver.setLatitude(10.0d);
        driver.setLicenseId("42");
        driver.setLongitude(10.0d);
        driver.setName("Name");
        driver.setTrips(new ArrayList<>());
        when(this.driverService.getDriver((String) any())).thenReturn(null);
        assertThatThrownBy(() -> {
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/drivers/get/{licenseNumber}", "42");
            MockMvcBuilders.standaloneSetup(this.driversController)
                    .build()
                    .perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isNotFound());
        })
                .isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(RuntimeException.class)
                .hasMessageContaining("Driver with license number ");
    }
}