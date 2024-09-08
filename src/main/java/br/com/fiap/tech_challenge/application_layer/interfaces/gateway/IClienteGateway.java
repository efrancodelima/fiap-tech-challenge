package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.domain_layer.exceptions.MyBusinessException;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.MyNotFoundException;

public interface IClienteGateway {

    public Cliente gravarCliente(Cliente cliente) throws MyBusinessException, Exception;

    public void atualizarCliente(Cliente cliente) throws MyNotFoundException, Exception;

    public void removerCliente(Cliente cliente) throws MyNotFoundException, Exception;

    public Cliente buscarClientePorCpf(Cpf cpf) throws MyNotFoundException, Exception;

}
