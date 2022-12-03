package com.anasatanaslopessantantospedra.dscatalog.controller.exceptions;

import com.anasatanaslopessantantospedra.dscatalog.service.exceptions.DataBaseException;
import com.anasatanaslopessantantospedra.dscatalog.service.exceptions.ResorceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

/*
    // Sem contrutor costrutor:
    @ExceptionHandler(ResorceNotFoundException.class)
    public ResponseEntity<StanderdErrer> resourceNotFound(ResorceNotFoundException e, HttpServletRequest request){
        StanderdErrer err=new StanderdErrer();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NO_CONTENT.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

 */
    //Com construtor:
    @ExceptionHandler(ResorceNotFoundException.class)
    public ResponseEntity<StanderdErrer> resourceNotFound(ResorceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status= HttpStatus.NOT_FOUND;
        StanderdErrer errer=new StanderdErrer(Instant.now(),status.value(),error, e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(errer);
    }
    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StanderdErrer> dataBase(DataBaseException e, HttpServletRequest request){
        String error = "DataBase error";
        HttpStatus status= HttpStatus.BAD_REQUEST;
        StanderdErrer errer=new StanderdErrer(Instant.now(),status.value(),error, e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(errer);
    }




}
