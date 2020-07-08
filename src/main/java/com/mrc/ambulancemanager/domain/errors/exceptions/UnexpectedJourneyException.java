package com.mrc.ambulancemanager.domain.errors.exceptions;

public class UnexpectedJourneyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnexpectedJourneyException(String journeyId) {
        super("Something bad happened creating the journey with id " + journeyId);
    }
}