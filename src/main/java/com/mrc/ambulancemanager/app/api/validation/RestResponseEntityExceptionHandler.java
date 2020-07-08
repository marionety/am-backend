package com.mrc.ambulancemanager.app.api.validation;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mrc.ambulancemanager.app.api.utils.ResponseExceptionMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    private static final String LINE_DESC = "Line ";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { InvalidFormatException.class })
    protected ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException exception) {
        return ResponseEntity.badRequest()
                .body(ResponseExceptionMapper.mapToJson(exception.getClass().getSimpleName(), LINE_DESC
                        + String.valueOf(exception.getLocation().getLineNr()) + ": " + exception.getOriginalMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(ResponseExceptionMapper.mapToJson(exception.getClass().getSimpleName(),
                getLocalizedErrors(exception.getBindingResult())));
    }

    private String getLocalizedErrors(BindingResult bindingResult) {
        String result = "";
        for (FieldError error : bindingResult.getFieldErrors()) {
            result = error.getField() + ": " + error.getDefaultMessage();
        }
        return result;
    }
}