package com.mrc.ambulancemanager.app.api.entities;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;

@Validated
public class AmbulanceRequest {

    @JsonIgnore
    private final int MIN_SEATS = 6;
    @JsonIgnore
    private final int MAX_SEATS = 8;

    @NotNull
    @Getter
    @JsonProperty("id")
    private final Long ambulanceRequestID;

    @Min(MIN_SEATS)
    @Max(MAX_SEATS)
    @NotNull
    @Getter
    private final Integer seats;

    @JsonCreator
    public AmbulanceRequest(@JsonProperty("id") final Long ambulanceRequestID,
            @JsonProperty("seats") @Valid final Integer seats) {
        this.ambulanceRequestID = ambulanceRequestID;
        this.seats = seats;
    }
}