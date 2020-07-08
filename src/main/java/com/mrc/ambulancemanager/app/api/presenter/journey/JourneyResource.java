package com.mrc.ambulancemanager.app.api.presenter.journey;

import javax.validation.Valid;

import com.mrc.ambulancemanager.app.api.entities.JourneyRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journey")
public interface JourneyResource {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity postJourney(@RequestBody @Valid JourneyRequest request);
}