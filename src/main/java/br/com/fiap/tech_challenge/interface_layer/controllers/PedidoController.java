package br.com.fiap.tech_challenge.interface_layer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.application_layer.use_cases.PedidoUseCase;
import br.com.fiap.tech_challenge.interface_layer.controllers.interfaces.IPedidoController;
import br.com.fiap.tech_challenge.interface_layer.gateways.PedidoGateway;
import jakarta.annotation.PostConstruct;

public class PedidoController implements IPedidoController {

    // Atributos
    @Autowired
    PedidoGateway gateway;
    PedidoUseCase pedidoUseCase;

    // Método de inicialização
    // A partir de um atributo injetado, inicializa um atributo não injetado
    @PostConstruct
    private void init() {
        this.pedidoUseCase = new PedidoUseCase(gateway);
    }

    @Override
    public ResponseEntity<String> criarPedido(Long cpf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criarPedido'");
    }

    @Override
    public ResponseEntity<String> criarPedido() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criarPedido'");
    }

}
