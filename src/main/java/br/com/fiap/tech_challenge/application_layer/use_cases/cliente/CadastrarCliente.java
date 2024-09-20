package br.com.fiap.tech_challenge.application_layer.use_cases.cliente;

import br.com.fiap.tech_challenge.application_layer.exceptions.ApplicationException;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IClienteGateway;
import br.com.fiap.tech_challenge.application_layer.services.Validar;
import br.com.fiap.tech_challenge.business_layer.entities.cliente.Cliente;

public final class CadastrarCliente {

    public static Cliente cadastrar(IClienteGateway gateway, Cliente cliente) throws Exception {
        Validar.notNull(cliente, EnumApplicationExceptions.CLIENTE_NULO);

        boolean clienteJaExiste = gateway.clienteJaExiste(cliente.getCpf());
        if (clienteJaExiste) {
            throw new ApplicationException(EnumApplicationExceptions.CLIENTE_JA_EXISTE.getMensagem());
        }

        return gateway.gravarCliente(cliente);
    }

}
