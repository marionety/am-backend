package com.mrc.ambulancemanager.domain.usecases.ambulance;

import java.util.List;

import com.mrc.ambulancemanager.app.api.entities.AmbulanceRequest;

public interface AmbulanceService {
    /**
     * Refresh all the data of ambulances and journeys with new data
     * 
     * @param request
     */
    void updateAmbulances(List<AmbulanceRequest> request);
}
