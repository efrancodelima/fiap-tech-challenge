package br.com.fiap.tech_challenge.interface_layer.gateways.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
