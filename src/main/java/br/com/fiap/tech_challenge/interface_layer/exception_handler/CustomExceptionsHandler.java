package br.com.fiap.tech_challenge.interface_layer.exception_handler;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.fiap.tech_challenge.application_layer.exceptions.ApplicationException;
import br.com.fiap.tech_challenge.application_layer.exceptions.ResourceNotFoundException;
import br.com.fiap.tech_challenge.business_layer.exceptions.BusinessRuleException;

@ControllerAdvice
public class CustomExceptionsHandler {

    // Status code 400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
        return processa(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Status code 400
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
            WebRequest request) {
        return processa(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Status code 400
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormatException(InvalidFormatException ex,
            WebRequest request) {
        return processa(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Status code 400
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseException(JsonParseException ex,
            WebRequest request) {
        return processa(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Status code 400
    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ErrorResponse> handleJsonMappingException(JsonMappingException ex,
            WebRequest request) {
        return processa(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Status code 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return processa(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Status code 422
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRulesException(BusinessRuleException ex) {
        return processa(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Status code 422
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException ex) {
        return processa(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Status code 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        return processa(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Método privado
    private ResponseEntity<ErrorResponse> processa(String msg, HttpStatus httpStatus) {
        var error = new ErrorResponse(httpStatus, msg);
        return new ResponseEntity<>(error, httpStatus);
    }

}
