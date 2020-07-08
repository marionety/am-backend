package com.mrc.ambulancemanager.app.api.presenter.ambulance;

import javax.validation.Valid;

import com.mrc.ambulancemanager.app.api.entities.AmbulanceRequest;
import com.mrc.ambulancemanager.app.api.entities.ResponseException;
import com.mrc.ambulancemanager.app.api.validation.ValidList;
import com.mrc.ambulancemanager.domain.usecases.ambulance.AmbulanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AmbulanceController implements AmbulanceResource {

    @Autowired
    private AmbulanceService ambulanceService;

    @Operation(summary = "Refresh ambulances and all associated data from journeys")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseException.class)) }) })
    @Override
    public ResponseEntity putAmbulances(@RequestBody @Valid ValidList<AmbulanceRequest> ambulancesRequest) {
        ambulanceService.updateAmbulances(ambulancesRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}