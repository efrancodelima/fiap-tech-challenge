package br.com.fiap.tech_challenge.domain_layer.exceptions;

public class MyBusinessException extends Exception {

    public MyBusinessException(String message) {
        super(message);
    }
}
