package com.mrc.ambulancemanager.domain.usecases.location;

import com.mrc.ambulancemanager.app.api.entities.LocationRequest;
import com.mrc.ambulancemanager.domain.entities.Location;

public interface LocationService {
    /**
     * Gets the location of a journey group
     * 
     * @param locationRequest
     * @return
     */
    Location obtainLocation(LocationRequest locationRequest);

}