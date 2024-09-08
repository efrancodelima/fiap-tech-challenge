package br.com.fiap.tech_challenge.external_layer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.external_layer.api.interfaces.IClienteApi;
import br.com.fiap.tech_challenge.interface_layer.controllers.ClienteController;
import br.com.fiap.tech_challenge.interface_layer.dtos.ClienteDto;

@RestController
@RequestMapping("/clientes")
public class ClienteApi implements IClienteApi {

    @Autowired
    private ClienteController controller;

    @Override
    public ResponseEntity<Cliente> cadastrarCliente(ClienteDto clienteDto) throws Exception {
        return controller.cadastrarCliente(clienteDto);
    }

    @Override
    public ResponseEntity<Cliente> buscarClientePorCpf(Long cpf) throws Exception {
        return controller.buscarClientePorCpf(cpf);
    }

}
