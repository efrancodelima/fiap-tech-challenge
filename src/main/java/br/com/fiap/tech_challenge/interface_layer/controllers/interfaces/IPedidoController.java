package br.com.fiap.tech_challenge.interface_layer.controllers.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.interface_layer.dtos.PedidoDto;

public interface IPedidoController {

    ResponseEntity<String> fazerCheckout(PedidoDto pedidoDto);

}
