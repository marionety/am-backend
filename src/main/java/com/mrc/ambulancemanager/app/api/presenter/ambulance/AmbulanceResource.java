package com.mrc.ambulancemanager.app.api.presenter.ambulance;

import javax.validation.Valid;

import com.mrc.ambulancemanager.app.api.entities.AmbulanceRequest;
import com.mrc.ambulancemanager.app.api.validation.ValidList;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ambulances")
public interface AmbulanceResource {

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity putAmbulances(@RequestBody @Valid ValidList<AmbulanceRequest> ambulancesRequest);
}