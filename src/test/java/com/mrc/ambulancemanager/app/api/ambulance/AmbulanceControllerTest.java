package com.mrc.ambulancemanager.app.api.ambulance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mrc.ambulancemanager.app.api.common.BaseControllerTest;
import com.mrc.ambulancemanager.app.api.entities.AmbulanceRequest;
import com.mrc.ambulancemanager.app.api.presenter.ambulance.AmbulanceController;
import com.mrc.ambulancemanager.domain.usecases.ambulance.AmbulanceService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(classes = AmbulanceController.class)
public class AmbulanceControllerTest extends BaseControllerTest {

    private final String URI = "/ambulances";

    @MockBean
    AmbulanceService ambulanceService;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    public void putAmbulances() throws Exception {
        AmbulanceRequest ambulance1 = new AmbulanceRequest((long) 32912912, 6);
        AmbulanceRequest ambulance2 = new AmbulanceRequest((long) 323121221, 7);
        List<AmbulanceRequest> ambulances = Stream.of(ambulance1, ambulance2).collect(Collectors.toList());
        String inputJson = super.mapToJson(ambulances);
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.put(URI).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void putAmbulancesSeatsInputError() throws Exception {
        AmbulanceRequest ambulance1 = new AmbulanceRequest((long) 32912912, 6);
        AmbulanceRequest ambulance2 = new AmbulanceRequest((long) 323121221, 13);
        List<AmbulanceRequest> ambulances = Stream.of(ambulance1, ambulance2).collect(Collectors.toList());
        String inputJson = super.mapToJson(ambulances);
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.put(URI).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
    }
}