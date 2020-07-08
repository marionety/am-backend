package com.mrc.ambulancemanager.app.api.presenter.transfer;

import com.mrc.ambulancemanager.app.api.entities.JourneyResponse;
import com.mrc.ambulancemanager.app.api.entities.ResponseException;
import com.mrc.ambulancemanager.app.api.entities.TransferRequest;
import com.mrc.ambulancemanager.app.api.exceptions.TransferRequestException;
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
public class TransferController implements TransferResource {

    @Autowired
    private JourneyService journeyService;

    @Operation(summary = "Ends the journey of a group of people, even if the journey was not related to any ambulance.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK, the journey was finished"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND, the group was not found or they have arrived before."),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST, request is wrong", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseException.class)) }) })
    @Override
    public ResponseEntity postTransfer(TransferRequest transferRequest) {
        try {
            if (transferRequest.getId() == null) {
                throw new TransferRequestException();
            } else {
                final JourneyResponse journeyResponse = JourneyResponse
                        .from(journeyService.finishJourney(transferRequest));
                return ResponseEntity.ok(journeyResponse);
            }
        } catch (final Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseExceptionMapper.mapToJson(exception.getClass().getSimpleName(), exception.getMessage()));
        }
    }
}