package com.mrc.ambulancemanager.app.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrc.ambulancemanager.domain.entities.Journey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JourneyResponse {

    @JsonProperty("id")
    private final Long journeyResponseID;

    @JsonProperty("people")
    private final Integer people;

    @JsonProperty("status")
    private final String status;

    public static JourneyResponse from(Journey journey) {
        return new JourneyResponse(journey.getId(), journey.getPeople(), journey.getStatus().toString());
    }
}
