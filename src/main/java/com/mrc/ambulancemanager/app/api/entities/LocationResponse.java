package com.mrc.ambulancemanager.app.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrc.ambulancemanager.domain.entities.Location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LocationResponse {

    @JsonProperty("id")
    private final Long locationResponseID;

    @JsonIgnore
    private final JourneyStatus status;

    public static LocationResponse from(Location location) {
        if (location == null) {
            return null;
        } else {
            try {
                return new LocationResponse(location.getId(), JourneyStatus.valueOf(location.getStatus().toString()));
            } catch (NullPointerException exception) {
                return null;
            } catch (IllegalArgumentException exception) {
                return null;
            }
        }

    }
}