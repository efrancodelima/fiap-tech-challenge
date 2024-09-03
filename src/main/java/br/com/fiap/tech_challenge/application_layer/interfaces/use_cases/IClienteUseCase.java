package br.com.fiap.tech_challenge.application_layer.interfaces.use_cases;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;

public interface IClienteUseCase {

    Cliente cadastrarCliente(Cliente cliente) throws Exception;

    Cliente buscarClientePorCpf(Cpf cpf) throws Exception;
}
