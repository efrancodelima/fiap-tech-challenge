package br.com.fiap.tech_challenge.interface_layer.controllers.adapters.request_adapters;

import br.com.fiap.tech_challenge.business_layer.entities.cliente.Cliente;
import br.com.fiap.tech_challenge.business_layer.entities.cliente.Cpf;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.ClienteDto;

public final class ClienteRequestAdapter {

    public static Cliente adaptar(ClienteDto clienteDto) throws Exception {
        Cpf cpf = new Cpf(clienteDto.getCpf());
        String nome = clienteDto.getNome();
        String email = clienteDto.getEmail();
        return new Cliente(null, cpf, nome, email);
    }

}
