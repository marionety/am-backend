package com.mrc.ambulancemanager.domain.errors.exceptions;

public class AlreadyCompletedJourneyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AlreadyCompletedJourneyException(String journeyId) {
        super("Journey with id " + journeyId + " is already completed");
    }
}