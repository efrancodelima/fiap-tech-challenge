package br.com.fiap.tech_challenge.interface_layer.gateways.jpa_mappers;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.ClienteJpa;

public final class ClienteMapper {

    // Métodos públicos
    public static ClienteJpa entidadeNegocioParaEntidadeJpa(Cliente cliente) {
        return new ClienteJpa(cliente.getId(), cliente.getCpf().getNumeroComDigito(), cliente.getNome(),
                cliente.getEmail());
    }

    public static Cliente entidadeJpaParaEntidadeNegocio(ClienteJpa clienteJpa) throws BusinessRulesExceptions {
        Cpf cpf = criarCpf(clienteJpa.getCpf());
        return new Cliente(clienteJpa.getId(), cpf, clienteJpa.getNome(), clienteJpa.getEmail());
    }

    // Métodos privados
    private static Cpf criarCpf(long cpf) throws BusinessRulesExceptions {
        byte digitoVerificador = (byte) (cpf % 100L);
        int numero = (int) (cpf / 100);
        return new Cpf(numero, digitoVerificador);
    }

}
