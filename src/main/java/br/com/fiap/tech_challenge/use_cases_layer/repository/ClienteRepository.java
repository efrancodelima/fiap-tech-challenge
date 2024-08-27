package br.com.fiap.tech_challenge.use_cases_layer.repository;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;

public interface ClienteRepository {
    public void gravarCliente(Cliente cliente);

    public Cliente buscarPorCpf(Cpf cpf);
}
