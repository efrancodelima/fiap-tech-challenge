package br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;

public final class ClienteResponseAdapter {

    public static ResponseEntity<String> adaptar(Cliente cliente, HttpStatus status) {
        return ObjectResponseAdapter.adaptar((Object) cliente, status);
    }

}
