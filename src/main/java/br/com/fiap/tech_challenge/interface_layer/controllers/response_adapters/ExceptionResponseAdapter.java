package br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesException;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.ResourceNotFoundException;

public final class ExceptionResponseAdapter {

    public static ResponseEntity<String> adaptar(Exception e) {

        HttpStatus status;

        if (e instanceof BusinessRulesException) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;

        } else if (e instanceof ResourceNotFoundException) {
            status = HttpStatus.NOT_FOUND;

        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        String msg = "Erro ao processar a requisição! " + e.getMessage();
        return new ResponseEntity<>(msg, status);
    }

}
