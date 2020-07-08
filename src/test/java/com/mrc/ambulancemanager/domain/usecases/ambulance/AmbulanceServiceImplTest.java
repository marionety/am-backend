package com.mrc.ambulancemanager.domain.usecases.ambulance;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import com.mrc.ambulancemanager.app.api.entities.AmbulanceRequest;
import com.mrc.ambulancemanager.data.entities.AmbulanceData;
import com.mrc.ambulancemanager.data.repositories.ambulance.AmbulanceRepository;
import com.mrc.ambulancemanager.data.repositories.journey.JourneyRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = AmbulanceServiceImpl.class)
public class AmbulanceServiceImplTest {

    @MockBean
    AmbulanceRepository ambulanceRepository;

    @MockBean
    JourneyRepository journeyRepository;

    List<AmbulanceRequest> request;

    @Autowired
    AmbulanceServiceImpl ambulanceService;

    @Test
    public void updateAmbulancesTest() {
        request = new ArrayList<AmbulanceRequest>();
        ambulanceService.updateAmbulances(request);
        verify(journeyRepository, times(1)).deleteAll();
        verify(ambulanceRepository, times(1)).deleteAll();
        verify(ambulanceRepository, times(1)).saveAll(new ArrayList<AmbulanceData>());
    }
}
