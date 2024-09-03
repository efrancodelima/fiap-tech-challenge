package br.com.fiap.tech_challenge.interface_layer.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IClienteGateway;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.ClienteJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_mappers.ClienteMapper;
import br.com.fiap.tech_challenge.interface_layer.gateways.repositories.IClienteJpaRepository;

@Component
public class ClienteGateway implements IClienteGateway {

    // Atributos
    @Autowired
    private final IClienteJpaRepository clienteJpaRepository;

    // Construtor
    public ClienteGateway(IClienteJpaRepository repositorioJpa) {
        this.clienteJpaRepository = repositorioJpa;
    }

    // Métodos públicos
    @Override
    public Cliente gravarCliente(Cliente cliente) throws Exception {
        ClienteJpa clienteJpa = converterParaEntidadeJpa(cliente);
        clienteJpa = clienteJpaRepository.save(clienteJpa);
        return converterParaEntidadeNegocio(clienteJpa);
    }

    @Override
    public Cliente atualizarCliente(Cliente cliente) throws Exception {
        // O repositório JPA usa o método save() para insert e update
        return gravarCliente(cliente);
    }

    @Override
    public void removerCliente(Cliente cliente) throws Exception {
        ClienteJpa clienteJpa = converterParaEntidadeJpa(cliente);
        clienteJpaRepository.delete(clienteJpa);
    }

    @Override
    public Cliente buscarClientePorCpf(Cpf cpf) throws BusinessRulesExceptions, Exception {
        ClienteJpa clienteJpa = clienteJpaRepository.findByCpf(cpf.getNumeroComDigito());
        return converterParaEntidadeNegocio(clienteJpa);
    }

    // Métodos privados
    private ClienteJpa converterParaEntidadeJpa(Cliente cliente) {
        return ClienteMapper.entidadeNegocioParaEntidadeJpa(cliente);
    }

    private Cliente converterParaEntidadeNegocio(ClienteJpa clienteJpa) throws BusinessRulesExceptions {
        return ClienteMapper.entidadeJpaParaEntidadeNegocio(clienteJpa);
    }

}
