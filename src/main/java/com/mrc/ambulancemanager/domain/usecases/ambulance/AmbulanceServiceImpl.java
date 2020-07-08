package com.mrc.ambulancemanager.domain.usecases.ambulance;

import java.util.List;

import com.mrc.ambulancemanager.app.api.entities.AmbulanceRequest;
import com.mrc.ambulancemanager.data.entities.AmbulanceData;
import com.mrc.ambulancemanager.data.repositories.ambulance.AmbulanceRepository;
import com.mrc.ambulancemanager.data.repositories.journey.JourneyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AmbulanceServiceImpl implements AmbulanceService {

    @Autowired
    AmbulanceRepository ambulanceRepository;

    @Autowired
    JourneyRepository journeyRepository;

    /**
     * {@inheritDoc}
     */
    public void updateAmbulances(List<AmbulanceRequest> request) {
        journeyRepository.deleteAll();
        ambulanceRepository.deleteAll();
        ambulanceRepository.saveAll(AmbulanceData.from(request));
    }
}
