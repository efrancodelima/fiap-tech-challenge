package br.com.fiap.tech_challenge.interface_layer.controllers.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.business_layer.entities.cliente.Cliente;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.ClienteDto;

public interface IClienteController {

    ResponseEntity<Cliente> cadastrarCliente(ClienteDto clienteDto) throws Exception;

    ResponseEntity<Cliente> buscarClientePorCpf(Long cpfLong) throws Exception;
}
