package br.com.fiap.tech_challenge.domain_layer.exceptions;

public class BusinessRulesException extends Exception {
    public BusinessRulesException(String message) {
        super(message);
    }
}
