package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;

public interface IClienteGateway {

    public Cliente gravarCliente(Cliente cliente) throws Exception;

    public Cliente atualizarCliente(Cliente cliente) throws Exception;

    public void removerCliente(Cliente cliente) throws Exception;

    public Cliente buscarClientePorCpf(Cpf cpf) throws BusinessRulesExceptions, Exception;

}
