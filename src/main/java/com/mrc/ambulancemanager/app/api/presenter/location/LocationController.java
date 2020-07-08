package com.mrc.ambulancemanager.app.api.presenter.location;

import com.mrc.ambulancemanager.app.api.entities.JourneyStatus;
import com.mrc.ambulancemanager.app.api.entities.LocationRequest;
import com.mrc.ambulancemanager.app.api.entities.LocationResponse;
import com.mrc.ambulancemanager.app.api.entities.ResponseException;
import com.mrc.ambulancemanager.domain.usecases.location.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LocationController implements LocationResource {

    @Autowired
    private LocationService locationService;

    @Operation(summary = "Gets the ambulance where the group is (if any)")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK, the assigned ambulance is returned"),
            @ApiResponse(responseCode = "204", description = "NO CONTENT, there is no assigned ambulance yet"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND, the group is not created yet or they have arrived"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST, request is wrong", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseException.class)) }) })
    @Override
    public ResponseEntity getLocation(LocationRequest locationRequest) {
        LocationResponse locationResponse = LocationResponse.from(locationService.obtainLocation(locationRequest));
        if (locationResponse == null || locationResponse.getStatus() == JourneyStatus.COMPLETED) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (locationResponse.getStatus() == JourneyStatus.WAITING) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else if (locationResponse.getStatus() == JourneyStatus.IN_PROGRESS) {
            return ResponseEntity.status(HttpStatus.OK).body(locationResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}