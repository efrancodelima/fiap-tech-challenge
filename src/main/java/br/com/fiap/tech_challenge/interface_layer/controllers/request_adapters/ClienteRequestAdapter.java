package br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesException;
import br.com.fiap.tech_challenge.interface_layer.dtos.ClienteDto;

public final class ClienteRequestAdapter {

    public static Cliente adaptar(ClienteDto clienteDto) throws BusinessRulesException {
        Cpf cpf = CpfRequestAdapter.adaptar(clienteDto.getCpf());
        String nome = clienteDto.getNome();
        String email = clienteDto.getEmail();
        return new Cliente(cpf, nome, email);
    }

}
