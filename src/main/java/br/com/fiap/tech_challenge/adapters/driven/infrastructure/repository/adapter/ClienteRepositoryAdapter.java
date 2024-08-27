package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ClienteEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.ClienteRepository;
import br.com.fiap.tech_challenge.core.application.ports.repository.ClienteRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteRepository repository;

    @Autowired
    public ClienteRepositoryAdapter(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ClienteEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<ClienteEntity> buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    @Override
    public void save(ClienteEntity cliente) {
        repository.save(cliente);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void excluirPorId(Long id) {
        repository.deleteById(id);
    }
}
