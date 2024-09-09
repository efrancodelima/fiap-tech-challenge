package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;

public interface IClienteGateway {

    Cliente gravarCliente(Cliente cliente) throws Exception;

    Cliente buscarClientePorCpf(Cpf cpf) throws Exception;

    boolean clienteJaExiste(Cpf cpf) throws Exception;

}
