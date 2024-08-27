package br.com.fiap.tech_challenge.use_cases_layer.use_cases;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.use_cases_layer.repository.ClienteRepository;

public class ClienteUseCase {

    public static void cadastrarCliente(ClienteRepository repository, Cliente cliente) {
        repository.gravarCliente(cliente);
    }

    public static Cliente buscarClientePorCpf(ClienteRepository repository, Cpf cpf){
        return repository.buscarPorCpf(cpf);
    }
}
