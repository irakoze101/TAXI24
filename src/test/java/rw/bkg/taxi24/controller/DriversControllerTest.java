package rw.bkg.taxi24.controller;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
    void testGetDrivers() throws Exception {
        when(this.driverService.getAllDrivers())
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/drivers");
        MockMvcBuilders.standaloneSetup(this.driversController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }
}