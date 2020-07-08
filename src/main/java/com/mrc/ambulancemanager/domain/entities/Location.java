package com.mrc.ambulancemanager.domain.entities;

import com.mrc.ambulancemanager.data.entities.JourneyData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Location {

    private Long id;

    private JourneyStatus status;

    public static Location from(JourneyData journeyData) {
        if (journeyData == null || journeyData.getAmbulance() == null) {
            return null;
        } else
            return new Location(journeyData.getAmbulance().getId(), journeyData.getStatus());
    }

}