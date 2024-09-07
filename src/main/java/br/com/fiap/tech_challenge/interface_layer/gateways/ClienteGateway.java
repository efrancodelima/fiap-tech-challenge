package br.com.fiap.tech_challenge.interface_layer.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IClienteGateway;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ClienteJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.ResourceNotFoundException;
import br.com.fiap.tech_challenge.interface_layer.gateways.mappers.ClienteMapper;
import br.com.fiap.tech_challenge.interface_layer.gateways.repositories.IClienteRepository;

@Component
public class ClienteGateway implements IClienteGateway {

    // Atributos
    @Autowired
    private IClienteRepository clienteJpaRepository;
    private final String CLIENTE_NAO_ENCONTRADO = "Não foi encontrado nenhum cliente para o código informado.";

    // Métodos públicos
    @Override
    public Cliente gravarCliente(Cliente cliente) throws Exception {
        ClienteJpa clienteJpa = ClienteMapper.mapearParaEntidadeJpa(cliente);
        clienteJpa = clienteJpaRepository.save(clienteJpa);
        return ClienteMapper.mapearParaEntidadeNegocio(clienteJpa);
    }

    @Override
    public void atualizarCliente(Cliente cliente) throws Exception {
        if (!clienteJpaRepository.existsById(cliente.getCodigo())) {
            throw new ResourceNotFoundException(CLIENTE_NAO_ENCONTRADO);
        }
        ClienteJpa clienteJpa = ClienteMapper.mapearParaEntidadeJpa(cliente);
        clienteJpaRepository.save(clienteJpa);
    }

    @Override
    public void removerCliente(Cliente cliente) throws Exception {
        if (!clienteJpaRepository.existsById(cliente.getCodigo())) {
            throw new ResourceNotFoundException(CLIENTE_NAO_ENCONTRADO);
        }
        ClienteJpa clienteJpa = ClienteMapper.mapearParaEntidadeJpa(cliente);
        clienteJpaRepository.delete(clienteJpa);
    }

    @Override
    public Cliente buscarClientePorCpf(Cpf cpf) throws Exception {
        ClienteJpa clienteJpa = clienteJpaRepository.findByCpf(cpf.getNumeroComDigito());
        return ClienteMapper.mapearParaEntidadeNegocio(clienteJpa);
    }

}
