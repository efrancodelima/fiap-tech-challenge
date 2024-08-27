package br.com.fiap.tech_challenge.core.domain.ports.in;

import br.com.fiap.tech_challenge.core.domain.model.Cliente;

public interface ClienteServicePort {
    void cadastrarCliente(Cliente cliente);
    Cliente buscarClientePorCPF(String cpf);
    void atualizarCliente(Cliente cliente);
    boolean existeCliente(Long id);
    String excluirCliente(Long id);
}
