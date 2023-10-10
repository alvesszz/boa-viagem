package br.com.etechoracio.viagem.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private record ErrorResponse(String error, LocalDateTime dateTime) {};

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TesteException.class)
    public org.springframework.web.ErrorResponse handleRuntimeException(TesteException ex) {
        return new org.springframework.web.ErrorResponse(ex.getMessage(), LocalDateTime.now());
    }
}





