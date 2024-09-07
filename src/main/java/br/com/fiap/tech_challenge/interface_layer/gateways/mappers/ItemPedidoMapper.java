package br.com.fiap.tech_challenge.interface_layer.gateways.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.ItemPedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ItemPedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ProdutoJpa;

public final class ItemPedidoMapper {

    // Métodos públicos
    public static ItemPedidoJpa mapearParaEntidadeNegocio(ItemPedido item) {
        ProdutoJpa produtoJpa = ProdutoMapper.mapearParaEntidadeJpa(item.getProduto());
        return new ItemPedidoJpa(produtoJpa, item.getQuantidade());
    }

    public static List<ItemPedidoJpa> mapearParaEntidadeJpa(List<ItemPedido> itens) {
        List<ItemPedidoJpa> result = new ArrayList<ItemPedidoJpa>();
        for (ItemPedido item : itens) {
            ItemPedidoJpa itemJpa = mapearParaEntidadeNegocio(item);
            result.add(itemJpa);
        }
        return result;
    }

    public static ItemPedido mapearParaEntidadeNegocio(ItemPedidoJpa itemJpa) throws Exception {
        Produto produto = ProdutoMapper.mapearParaEntidadeNegocio(itemJpa.getProduto());
        return new ItemPedido(produto, itemJpa.getQuantidade());
    }

    public static List<ItemPedido> mapearParaEntidadeNegocio(List<ItemPedidoJpa> itensJpa)
            throws Exception {
        List<ItemPedido> result = new ArrayList<ItemPedido>();
        for (ItemPedidoJpa itemJpa : itensJpa) {
            ItemPedido item = mapearParaEntidadeNegocio(itemJpa);
            result.add(item);
        }
        return result;
    }

}
