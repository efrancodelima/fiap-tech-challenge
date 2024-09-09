package br.com.fiap.tech_challenge.interface_layer.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IClienteGateway;
import br.com.fiap.tech_challenge.business_layer.entities.Cliente;
import br.com.fiap.tech_challenge.business_layer.entities.Cpf;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ClienteJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.mappers.ClienteMapper;
import br.com.fiap.tech_challenge.interface_layer.gateways.repositories.IClienteRepository;

@Component
public class ClienteGateway implements IClienteGateway {

    // Atributos
    @Autowired
    private IClienteRepository clienteJpaRepository;

    // Métodos públicos
    @Override
    public Cliente gravarCliente(Cliente cliente) throws Exception {

        ClienteJpa clienteJpa = ClienteMapper.getClienteJpa(cliente);

        clienteJpa = clienteJpaRepository.save(clienteJpa);

        return ClienteMapper.getCliente(clienteJpa);
    }

    @Override
    public Cliente buscarClientePorCpf(Cpf cpf) throws Exception {

        ClienteJpa clienteJpa = clienteJpaRepository.findByCpf(cpf.pegarNumeroComDigito());

        if (clienteJpa != null) {
            return ClienteMapper.getCliente(clienteJpa);
        } else {
            return null;
        }
    }

    @Override
    public boolean clienteJaExiste(Cpf cpf) throws Exception {
        return clienteJpaRepository.existsByCpf(cpf.pegarNumeroComDigito());
    }

}
