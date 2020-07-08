package com.mrc.ambulancemanager.app.api.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class JourneyRequest {

    @NotNull
    @JsonProperty("id")
    private final Long journeyRequestID;

    @NotNull
    @Min(1)
    @JsonProperty("people")
    private final Integer people;

    @JsonCreator
    public JourneyRequest(@JsonProperty("id") final Long journeyRequestID,
            @JsonProperty("people") final Integer people) {
        this.journeyRequestID = journeyRequestID;
        this.people = people;
    }
}