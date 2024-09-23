package com.duxchallenge.apifootballteams.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<?> handleTeamNotFoundException(){
        ErrorDetails errorDetails = new ErrorDetails("Equipo no encontrado", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
}
