package com.mrc.ambulancemanager.domain.usecases.journey;

import com.mrc.ambulancemanager.app.api.entities.JourneyRequest;
import com.mrc.ambulancemanager.app.api.entities.TransferRequest;
import com.mrc.ambulancemanager.domain.entities.Journey;

public interface JourneyService {

    /**
     * Creates a journey group and sets the status depending on ambulance
     * availability
     * 
     * @param request
     * @return the journey group created
     */
    public Journey createJourney(JourneyRequest request);

    /**
     * Finishes a journey and updates the status of other waiting journey groups (if
     * any)
     * 
     * @param request
     * @return
     */
    public Journey finishJourney(TransferRequest request);
}