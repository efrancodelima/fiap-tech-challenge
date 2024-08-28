package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;

public interface IClienteGateway {
    public Cliente gravarCliente(Cliente cliente);

    public Cliente atualizarCliente(Cliente cliente);

    public void removerCliente(Cliente cliente);

    public Cliente buscarClientePorCpf(Cpf cpf);
}
