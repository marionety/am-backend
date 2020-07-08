package com.mrc.ambulancemanager.app.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrc.ambulancemanager.app.api.entities.ResponseException;

public class ResponseExceptionMapper {

    public static String mapToJson(String name, String message) {
        ObjectMapper mapper = new ObjectMapper();
        ResponseException responseException = new ResponseException(name, message);
        try {
            return mapper.writeValueAsString(responseException);
        } catch (JsonProcessingException e) {
            return "An unexpected exception ocurred";
        }
    }
}