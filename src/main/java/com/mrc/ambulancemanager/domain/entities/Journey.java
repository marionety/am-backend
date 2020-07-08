package com.mrc.ambulancemanager.domain.entities;

import com.mrc.ambulancemanager.app.api.entities.JourneyRequest;
import com.mrc.ambulancemanager.data.entities.JourneyData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Journey {

    private Long id;

    private Integer people;

    private JourneyStatus status;

    public static Journey from(JourneyRequest request) {
        if (request == null) {
            return null;
        } else
            return new Journey(request.getJourneyRequestID(), request.getPeople(), JourneyStatus.WAITING);
    }

    public static Journey from(JourneyData journeyData) {
        if (journeyData == null) {
            return null;
        } else
            return new Journey(journeyData.getId(), journeyData.getPeople(), journeyData.getStatus());
    }
}