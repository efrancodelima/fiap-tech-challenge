package br.com.fiap.tech_challenge.application_layer.use_cases;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IClienteGateway;
import br.com.fiap.tech_challenge.application_layer.interfaces.use_cases.IClienteUseCase;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.domain_layer.exceptions.ClienteExceptions;

public class ClienteUseCase implements IClienteUseCase {

    // Atributos
    private IClienteGateway gateway;

    // Construtor
    public ClienteUseCase(IClienteGateway gateway) {
        this.gateway = gateway;
    }

    // Métodos
    @Override
    public void cadastrarCliente(Cliente cliente) {
        gateway.gravarCliente(cliente);
    }

    @Override
    public Cliente buscarClientePorCpf(Cpf cpf) {
        return gateway.buscarClientePorCpf(cpf);
    }
}
