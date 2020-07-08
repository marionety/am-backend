package com.mrc.ambulancemanager.app.api.presenter.journey;

import javax.validation.Valid;

import com.mrc.ambulancemanager.app.api.entities.JourneyRequest;
import com.mrc.ambulancemanager.app.api.entities.JourneyResponse;
import com.mrc.ambulancemanager.app.api.entities.ResponseException;
import com.mrc.ambulancemanager.app.api.utils.ResponseExceptionMapper;
import com.mrc.ambulancemanager.domain.usecases.journey.JourneyService;

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
public class JourneyController implements JourneyResource {

    @Autowired
    private JourneyService journeyService;

    @Operation(summary = "Creates a journey for a group of people")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK, the group is created sucessfully"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST, request is wrong", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseException.class)) }) })
    @Override
    public ResponseEntity postJourney(@Valid final JourneyRequest request) {
        try {
            final JourneyResponse journeyResponse = JourneyResponse.from(journeyService.createJourney(request));
            return ResponseEntity.ok(journeyResponse);
        } catch (final Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseExceptionMapper.mapToJson(exception.getClass().getSimpleName(), exception.getMessage()));
        }
    }
}