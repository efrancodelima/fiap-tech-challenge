package br.com.fiap.tech_challenge.interface_layer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.use_cases.ClienteUseCase;
import br.com.fiap.tech_challenge.business_layer.entities.Cliente;
import br.com.fiap.tech_challenge.business_layer.entities.Cpf;
import br.com.fiap.tech_challenge.interface_layer.controllers.adapters.request_adapters.ClienteRequestAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.adapters.response_adapters.ClienteResponseAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.ClienteDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.interfaces.IClienteController;
import br.com.fiap.tech_challenge.interface_layer.gateways.ClienteGateway;
import jakarta.annotation.PostConstruct;

@Component
public class ClienteController implements IClienteController {

    // Atributos
    @Autowired
    ClienteGateway gateway;
    ClienteUseCase clienteUseCase;

    // Método de inicialização
    // A partir de um atributo injetado, inicializa um atributo não injetado
    @PostConstruct
    private void init() {
        this.clienteUseCase = new ClienteUseCase(gateway);
    }

    // Métodos públicos
    @Override
    public ResponseEntity<Cliente> cadastrarCliente(ClienteDto clienteDto) throws Exception {

        Cliente cliente = ClienteRequestAdapter.adaptar(clienteDto);
        cliente = clienteUseCase.cadastrarCliente(cliente);
        return ClienteResponseAdapter.adaptar(cliente, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Cliente> buscarClientePorCpf(Long cpfLong) throws Exception {

        Cpf cpf = new Cpf(cpfLong);
        Cliente cliente = clienteUseCase.buscarClientePorCpf(cpf);
        return ClienteResponseAdapter.adaptar(cliente, HttpStatus.OK);
    }
}