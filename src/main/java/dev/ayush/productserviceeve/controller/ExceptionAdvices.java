package dev.ayush.productserviceeve.controller;

import dev.ayush.productserviceeve.dtos.ErrorResponseDTO;
import dev.ayush.productserviceeve.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//NOTE: Typically exception handlers are meant to be inside controllers.

// controllerAdvice kind of methods are declared globally. These are basically advices to any controller.
// Hey Controller, if you ever end up with exception as NotFoundException, then please call this particular method.
@ControllerAdvice
public class ExceptionAdvices {
    // Use ExceptionHandler for handling the error messages and write in @ControllerAdvice.
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(Exception e){
        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO();
        errorResponseDTO.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }
}
