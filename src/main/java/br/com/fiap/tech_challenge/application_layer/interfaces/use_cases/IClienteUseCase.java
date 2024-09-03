package br.com.fiap.tech_challenge.application_layer.interfaces.use_cases;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;

public interface IClienteUseCase {

    void cadastrarCliente(Cliente cliente) throws BusinessRulesExceptions, Exception;

    Cliente buscarClientePorCpf(Cpf cpf) throws BusinessRulesExceptions, Exception;
}
