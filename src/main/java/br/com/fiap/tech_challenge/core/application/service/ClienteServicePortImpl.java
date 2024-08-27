package br.com.fiap.tech_challenge.core.application.service;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ClienteEntity;
import br.com.fiap.tech_challenge.core.application.exception.cliente.*;
import br.com.fiap.tech_challenge.core.application.mapper.ClienteMapper;
import br.com.fiap.tech_challenge.core.application.ports.repository.ClienteRepositoryPort;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.ports.in.ClienteServicePort;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.fiap.tech_challenge.core.application.constant.ClienteExceptionConstante.*;

@Service
@Log4j2
public class ClienteServicePortImpl implements ClienteServicePort {

    private final ClienteRepositoryPort repository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteServicePortImpl(ClienteRepositoryPort clienteRepositoryPort, ClienteMapper clienteMapper) {
        this.repository = clienteRepositoryPort;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public void cadastrarCliente(Cliente cliente) {
        if(repository.buscarPorCpf(cliente.getCpf()).isPresent()) {
            throw new ClienteJaCadastradoException(CLIENTE_JA_CADASTRADO_EXCEPTION);
        }

        try {
            cliente.setCpf(cliente.getCpf());
            cliente.getEnderecos().forEach(endereco -> {
                endereco.setCep(endereco.getCep());
            });
            cliente.getTelefones().forEach(telefone -> {
                telefone.setNumero(telefone.getNumero());
            });

            ClienteEntity clienteEntity = clienteMapper.toEntity(cliente);
            repository.save(clienteEntity);
        } catch (Exception e) {
            log.error(e);
            throw new ErroAoCadastrarClienteException(ERRO_AO_CADASTRAR_CLIENTE_EXCEPTION);
        }
    }

    @Override
    public Cliente buscarClientePorCPF(String cpf) {
        Optional<ClienteEntity> clienteEntity = repository.buscarPorCpf(cpf);
        if(clienteEntity.isPresent()) {
            return clienteMapper.toDTO(clienteEntity.get());
        }
        else {
            throw new ClienteNaoEncontradoException(CLIENTE_NAO_ENCONTRADO_EXCEPTION);
        }
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        if(!repository.existsById(cliente.getId())) {
            throw new ClienteNaoEncontradoException(CLIENTE_NAO_ENCONTRADO_EXCEPTION);
        }

        Optional<ClienteEntity> clienteEntity = repository.findById(cliente.getId());
        if(clienteEntity.isPresent()) {
            try {
                ClienteEntity entity = clienteMapper.toEntity(cliente);
                repository.save(entity);
            }
            catch (Exception e) {
                log.error(e);
                throw new ErroAoAtualizarAsInformacoesDoClienteException(
                        ERRO_AO_ATUALIZAR_AS_INFORMACOES_DO_CLIENTE_EXCEPTION);
            }
        }
    }

    @Override
    public boolean existeCliente(Long id) {
        return repository.existsById(id);
    }

    @Override
    public String excluirCliente(Long id) {
        if(!repository.existsById(id)) {
            throw new ClienteNaoEncontradoException(CLIENTE_NAO_ENCONTRADO_EXCEPTION);
        }

        try {
            repository.excluirPorId(id);
            return "Cliente excluído com sucesso!";
        }
        catch (Exception e) {
            log.error(e);
            throw new ErroAoExcluirClienteException(ERRO_AO_EXCLUIR_CLIENTE_EXCEPTION);
        }
    }


}
