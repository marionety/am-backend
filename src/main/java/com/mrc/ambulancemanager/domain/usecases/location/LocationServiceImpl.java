package com.mrc.ambulancemanager.domain.usecases.location;

import java.util.Optional;

import com.mrc.ambulancemanager.app.api.entities.LocationRequest;
import com.mrc.ambulancemanager.data.entities.JourneyData;
import com.mrc.ambulancemanager.data.repositories.journey.JourneyRepository;
import com.mrc.ambulancemanager.domain.entities.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    @Autowired
    JourneyRepository journeyRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Location obtainLocation(LocationRequest locationRequest) {
        Optional<JourneyData> journeyDataOptional = journeyRepository.findById(locationRequest.getId());
        if (journeyDataOptional.isPresent()) {
            return Location.from(journeyDataOptional.get());
        } else
            return null;
    }

}