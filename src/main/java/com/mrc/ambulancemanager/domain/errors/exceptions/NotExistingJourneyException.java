package com.mrc.ambulancemanager.domain.errors.exceptions;

public class NotExistingJourneyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotExistingJourneyException(String journeyId) {
        super("Journey with id " + journeyId + " does not exist");
    }
}