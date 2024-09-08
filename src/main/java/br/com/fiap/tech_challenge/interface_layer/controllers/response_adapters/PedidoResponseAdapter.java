package br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;

public final class PedidoResponseAdapter {

    public static ResponseEntity<Pedido> adaptar(Pedido pedido) {
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

}