package br.com.fiap.tech_challenge.interface_layer.gateways.mappers;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ClienteJpa;

public final class ClienteMapper {

    // Métodos públicos
    public static ClienteJpa mapearParaEntidadeJpa(Cliente cliente) {
        return new ClienteJpa(cliente.getCodigo(), cliente.getCpf().pegarNumeroComDigito(), cliente.getNome(),
                cliente.getEmail());
    }

    public static Cliente mapearParaEntidadeNegocio(ClienteJpa clienteJpa) throws Exception {
        Cpf cpf = criarCpf(clienteJpa.getCpf());
        return new Cliente(clienteJpa.getCodigo(), cpf, clienteJpa.getNome(), clienteJpa.getEmail());
    }

    // Métodos privados
    private static Cpf criarCpf(long cpf) throws Exception {
        byte digitoVerificador = (byte) (cpf % 100L);
        int numero = (int) (cpf / 100);
        return new Cpf(numero, digitoVerificador);
    }

}
