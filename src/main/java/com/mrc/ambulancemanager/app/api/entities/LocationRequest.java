package com.mrc.ambulancemanager.app.api.entities;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LocationRequest {
    @NotNull
    private final Long id;
}