package com.mrc.ambulancemanager.app.api.exceptions;

public class TransferRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MESSAGE = "Wrong transfer request, please provide an \"id\" parameter with a valid value";

    public TransferRequestException(String message) {
        super(message);
    }

    public TransferRequestException() {
        super(DEFAULT_MESSAGE);
    }
}