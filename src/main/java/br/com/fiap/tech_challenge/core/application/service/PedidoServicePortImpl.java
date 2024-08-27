package br.com.fiap.tech_challenge.core.application.service;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ItemPedidoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.PedidoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.core.application.exception.pedido.ErroAoCadastrarPedidoException;
import br.com.fiap.tech_challenge.core.application.exception.pedido.NenhumPedidoEncontradoException;
import br.com.fiap.tech_challenge.core.application.exception.produto.NenhumProdutoEncontradoException;
import br.com.fiap.tech_challenge.core.application.mapper.PedidoMapper;
import br.com.fiap.tech_challenge.core.application.ports.repository.PedidoRepositoryPort;
import br.com.fiap.tech_challenge.core.application.ports.repository.ProdutoRepositoryPort;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import br.com.fiap.tech_challenge.core.domain.model.enums.StatusPedido;
import br.com.fiap.tech_challenge.core.domain.ports.in.PedidoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.fiap.tech_challenge.core.application.constant.PedidoExceptionConstante.ERRO_AO_CADASTRAR_PEDIDO_EXCEPTION;
import static br.com.fiap.tech_challenge.core.application.constant.PedidoExceptionConstante.NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION;
import static br.com.fiap.tech_challenge.core.application.constant.ProdutoExceptionConstante.ERRO_AO_CONSULTAR_PRODUTO_POR_ID_EXCEPTION;

@Service
public class PedidoServicePortImpl implements PedidoServicePort {

  private final PedidoRepositoryPort pedidoRepositoryPort;
  private final ProdutoRepositoryPort produtoRepositoryPort;
  private final PedidoMapper pedidoMapper;

  @Autowired
  public PedidoServicePortImpl(PedidoRepositoryPort pedidoRepositoryPort,
                               ProdutoRepositoryPort produtoRepositoryPort,
                               PedidoMapper pedidoMapper) {
    this.pedidoRepositoryPort = pedidoRepositoryPort;
    this.produtoRepositoryPort = produtoRepositoryPort;
    this.pedidoMapper = pedidoMapper;
  }

  @Override
  public void cadastrarPedido(Pedido pedido) {
    PedidoEntity entity = new PedidoEntity();
    try {
      pedido.getItens().forEach(item -> {
        if (produtoRepositoryPort.findById(item.getIdProduto()).isEmpty()) {
          throw new NenhumProdutoEncontradoException(
              ERRO_AO_CONSULTAR_PRODUTO_POR_ID_EXCEPTION +
                  item.getIdProduto().toString());
        }

        Optional<ProdutoEntity> produtoEntity = produtoRepositoryPort.findById(item.getIdProduto());
        ItemPedidoEntity itemPedidoEntity;
        if(produtoEntity.isPresent()) {
          itemPedidoEntity = buildItemPedidoEntity(produtoEntity.get(), item.getQuantidade());
          entity.getItensPedido().add(itemPedidoEntity);
        }

      });

      // Valor mockado por conta do FAKE CHECKOUT
      entity.setSituacao(StatusPedido.PAGO);
      entity.setDataPedido(new Date());
      entity.setValorTotalPedido(getValorTotalPedido(entity.getItensPedido()));

      pedidoRepositoryPort.cadastrarPedidos(entity);

    } catch (Exception e) {
      throw new ErroAoCadastrarPedidoException(ERRO_AO_CADASTRAR_PEDIDO_EXCEPTION);
    }

  }

  @Override
  public List<Pedido> listarPedidos() {
    List<PedidoEntity> pedidoEntity = pedidoRepositoryPort.listaPedidos();
    if (pedidoEntity.isEmpty()) {
      throw new NenhumPedidoEncontradoException(NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION);
    }

    List<Pedido> pedidos = new ArrayList<>();
    pedidoEntity.forEach(entity -> {
      Pedido pedido = pedidoMapper.toDTO(entity);
      pedidos.add(pedido);
    });

    return pedidos;
  }

  private ItemPedidoEntity buildItemPedidoEntity(ProdutoEntity produtoEntity, int quantidade) {
    return ItemPedidoEntity.builder()
        .nome(produtoEntity.getNome())
        .descricao(produtoEntity.getDescricao())
        .valorUnitario(produtoEntity.getPreco())
        .quantidade(quantidade)
        .valorTotalPedido(produtoEntity.getPreco()
            .multiply(BigDecimal.valueOf(quantidade)))
        .build();
  }

  public BigDecimal getValorTotalPedido(List<ItemPedidoEntity> itensPedido) {
    return itensPedido.stream()
        .map(ItemPedidoEntity::getValorTotalPedido)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
