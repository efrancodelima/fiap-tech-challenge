package br.com.fiap.tech_challenge.core.application.ports.repository;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.PedidoEntity;

import java.util.List;

public interface PedidoRepositoryPort {
    void cadastrarPedidos(PedidoEntity pedido);
    List<PedidoEntity> listaPedidos();

}
