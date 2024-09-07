package br.com.fiap.tech_challenge.interface_layer.controllers.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.interface_layer.dtos.ClienteDto;

public interface IClienteController {

    ResponseEntity<String> cadastrarCliente(ClienteDto clienteDto);

    ResponseEntity<String> buscarClientePorCpf(Long cpfLong);
}
