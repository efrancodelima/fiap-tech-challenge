package br.com.fiap.tech_challenge.interface_layer.controllers.exception_handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponse {

    private int code;
    private String status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.status = httpStatus.getReasonPhrase();
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}
