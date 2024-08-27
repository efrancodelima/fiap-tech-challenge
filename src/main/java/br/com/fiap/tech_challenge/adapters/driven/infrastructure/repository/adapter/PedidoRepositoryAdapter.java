package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.PedidoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.PedidoRepository;
import br.com.fiap.tech_challenge.core.application.ports.repository.PedidoRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoRepository repository;

    @Autowired
    public PedidoRepositoryAdapter(PedidoRepository repository) {
        this.repository = repository;

    }

    @Override
    public void cadastrarPedidos(PedidoEntity pedido) {
        repository.save(pedido);
    }

    @Override
    public List<PedidoEntity> listaPedidos() {
        return repository.findAll();
    }
}
