package br.com.fiap.tech_challenge.interface_layer.controllers.adapters.response_adapters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;

public final class ClienteResponseAdapter {

    public static ResponseEntity<Cliente> adaptar(Cliente cliente, HttpStatus status) {
        return new ResponseEntity<>(cliente, status);
    }

}
