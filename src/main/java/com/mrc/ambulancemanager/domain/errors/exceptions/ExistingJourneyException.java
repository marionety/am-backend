package com.mrc.ambulancemanager.domain.errors.exceptions;

public class ExistingJourneyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExistingJourneyException(String journeyId) {
        super("Journey with id " + journeyId + " already exists");
    }
}