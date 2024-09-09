package br.com.fiap.tech_challenge.interface_layer.gateways.mappers;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ClienteJpa;

public final class ClienteMapper {

    public static ClienteJpa getClienteJpa(Cliente cliente) {
        return new ClienteJpa(cliente.getCodigo(), cliente.getCpf().pegarNumeroComDigito(), cliente.getNome(),
                cliente.getEmail());
    }

    public static Cliente getCliente(ClienteJpa clienteJpa) throws Exception {
        Cpf cpf = new Cpf(clienteJpa.getCpf());
        return new Cliente(clienteJpa.getCodigo(), cpf, clienteJpa.getNome(), clienteJpa.getEmail());
    }

}
