package br.com.fiap.tech_challenge.interface_layer.controllers.exception_handler;

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

import br.com.fiap.tech_challenge.domain_layer.exceptions.MyBusinessException;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.MyNotFoundException;

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
    @ExceptionHandler(MyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMyNotFoundException(MyNotFoundException ex) {
        return processa(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Status code 422
    @ExceptionHandler(MyBusinessException.class)
    public ResponseEntity<ErrorResponse> handleMyBusinessException(MyBusinessException ex) {
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
