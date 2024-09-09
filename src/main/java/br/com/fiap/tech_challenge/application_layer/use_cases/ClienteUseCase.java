package br.com.fiap.tech_challenge.application_layer.use_cases;

import br.com.fiap.tech_challenge.application_layer.exceptions.ApplicationException;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IClienteGateway;
import br.com.fiap.tech_challenge.application_layer.use_cases.interfaces.IClienteUseCase;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;

public class ClienteUseCase implements IClienteUseCase {

    // Atributos
    private IClienteGateway gateway;

    // Construtor
    public ClienteUseCase(IClienteGateway gateway) {
        this.gateway = gateway;
    }

    // Métodos públicos
    @Override
    public Cliente cadastrarCliente(Cliente cliente) throws Exception {

        Validar.notNull(cliente, EnumApplicationExceptions.CLIENTE_NULO);

        validarClienteNaoCadastrado(cliente, EnumApplicationExceptions.CLIENTE_JA_EXISTE);
        return gateway.gravarCliente(cliente);
    }

    @Override
    public Cliente buscarClientePorCpf(Cpf cpf) throws Exception {

        Validar.notNull(cpf, EnumApplicationExceptions.CPF_NULO);

        Cliente cliente = gateway.buscarClientePorCpf(cpf);
        Validar.notNull(cliente, EnumNotFoundExceptions.CLIENTE_NAO_ENCONTRADO);

        return cliente;
    }

    // Métodos privados
    private void validarClienteNaoCadastrado(Cliente cliente, EnumApplicationExceptions excecao) throws Exception {

        boolean clienteJaExiste = gateway.clienteJaExiste(cliente.getCpf());
        if (clienteJaExiste) {
            throw new ApplicationException(excecao.getMensagem());
        }
    }

}
