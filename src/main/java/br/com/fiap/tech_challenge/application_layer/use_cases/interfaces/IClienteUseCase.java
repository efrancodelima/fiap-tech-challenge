package br.com.fiap.tech_challenge.application_layer.use_cases.interfaces;

import br.com.fiap.tech_challenge.business_layer.entities.Cliente;
import br.com.fiap.tech_challenge.business_layer.entities.Cpf;

public interface IClienteUseCase {

    Cliente cadastrarCliente(Cliente cliente) throws Exception;

    Cliente buscarClientePorCpf(Cpf cpf) throws Exception;
}
