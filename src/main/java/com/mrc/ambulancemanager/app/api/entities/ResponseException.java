package com.mrc.ambulancemanager.app.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseException {
    @JsonProperty("exception")
    private final String type;

    @JsonProperty("message")
    private final String message;
}