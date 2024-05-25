package com.tmaxnoda.bookinventory.Core.Application.Exceptions;

import com.tmaxnoda.bookinventory.Core.Application.Responses.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public  class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<GenericResponse<?>> handleException(GenericException exc){
       //create a GenreErrorResponse
        GenericResponse<?> error = new GenericResponse<>();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<GenericResponse<?>> handleException(Exception exceptions){
        //create a GenreErrorResponse
        HttpStatus ExceptionhttpStatus = determineHttpStatus(exceptions);
        GenericResponse<?> error = new GenericResponse<>();
        error.setStatus(ExceptionhttpStatus.value());
        error.setMessage(exceptions.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, ExceptionhttpStatus);
    }


   

    private HttpStatus determineHttpStatus(Exception ex) {
        if (ex instanceof HttpClientErrorException.NotFound) {
            return HttpStatus.NOT_FOUND;
        } else if (ex instanceof HttpClientErrorException.BadRequest) {
            return HttpStatus.BAD_REQUEST;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}

