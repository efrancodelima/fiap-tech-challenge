package br.com.fiap.tech_challenge.interface_layer.controllers.interfaces;

import org.springframework.http.ResponseEntity;

public interface IPedidoController {

    ResponseEntity<String> criarPedido(Long cpf);

    ResponseEntity<String> criarPedido();

}
