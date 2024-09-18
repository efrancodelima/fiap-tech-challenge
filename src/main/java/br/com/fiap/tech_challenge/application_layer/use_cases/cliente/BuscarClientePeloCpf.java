package br.com.fiap.tech_challenge.application_layer.use_cases.cliente;

import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IClienteGateway;
import br.com.fiap.tech_challenge.application_layer.services.Validar;
import br.com.fiap.tech_challenge.business_layer.entities.Cliente;
import br.com.fiap.tech_challenge.business_layer.entities.Cpf;

public final class BuscarClientePeloCpf {

    public static Cliente buscar(IClienteGateway gateway, Cpf cpf) throws Exception {
        Validar.notNull(cpf, EnumApplicationExceptions.CPF_NULO);

        Cliente cliente = gateway.buscarClientePorCpf(cpf);
        Validar.notNull(cliente, EnumNotFoundExceptions.CLIENTE_NAO_ENCONTRADO);

        return cliente;
    }

}
