package br.com.fiap.tech_challenge.interface_layer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.use_cases.ClienteUseCase;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.interface_layer.controllers.interfaces.IClienteController;
import br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters.ClienteRequestAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters.CpfRequestAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters.ClienteResponseAdapter;
import br.com.fiap.tech_challenge.interface_layer.dtos.ClienteDto;
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

        Cpf cpf = CpfRequestAdapter.adaptar(cpfLong);
        Cliente cliente = clienteUseCase.buscarClientePorCpf(cpf);
        return ClienteResponseAdapter.adaptar(cliente, HttpStatus.OK);
    }
}