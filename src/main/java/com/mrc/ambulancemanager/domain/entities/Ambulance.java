package com.mrc.ambulancemanager.domain.entities;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Valid
public class Ambulance {
    @JsonIgnore
    private final int MIN_SEATS = 6;
    @JsonIgnore
    private final int MAX_SEATS = 8;

    @NotNull
    @Getter
    private final Long id;

    @Min(MIN_SEATS)
    @Max(MAX_SEATS)
    @Getter
    private final Integer seats;

    @JsonCreator
    public Ambulance(@JsonProperty("id") Long id, @JsonProperty("seats") Integer seats) {
        this.id = id;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "{" + "\nid: " + id + "\nseats: " + seats + "\n}";
    }
}