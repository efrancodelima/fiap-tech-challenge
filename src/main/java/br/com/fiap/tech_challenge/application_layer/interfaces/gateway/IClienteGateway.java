package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;

public interface IClienteGateway {

    public Cliente gravarCliente(Cliente cliente) throws Exception;

    public void atualizarCliente(Cliente cliente) throws Exception;

    public void removerCliente(Cliente cliente) throws Exception;

    public Cliente buscarClientePorCpf(Cpf cpf) throws Exception;

}
