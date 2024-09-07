package br.com.fiap.tech_challenge.interface_layer.gateways.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.ItemPedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ItemPedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ProdutoJpa;

public final class ItemPedidoMapper {

    // Métodos públicos
    public static ItemPedidoJpa adaptarParaEntidadeNegocio(ItemPedido item) {
        ProdutoJpa produtoJpa = ProdutoMapper.mapperParaEntidadeJpa(item.getProduto());
        return new ItemPedidoJpa(produtoJpa, item.getQuantidade());
    }

    public static List<ItemPedidoJpa> adaptarParaEntidadeJpa(List<ItemPedido> itens) {
        List<ItemPedidoJpa> result = new ArrayList<ItemPedidoJpa>();
        for (ItemPedido item : itens) {
            ItemPedidoJpa itemJpa = adaptarParaEntidadeNegocio(item);
            result.add(itemJpa);
        }
        return result;
    }

    public static ItemPedido adaptarParaEntidadeNegocio(ItemPedidoJpa itemJpa) throws Exception {
        Produto produto = ProdutoMapper.mapperParaEntidadeNegocio(itemJpa.getProduto());
        return new ItemPedido(produto, itemJpa.getQuantidade());
    }

    public static List<ItemPedido> adaptarParaEntidadeNegocio(List<ItemPedidoJpa> itensJpa)
            throws Exception {
        List<ItemPedido> result = new ArrayList<ItemPedido>();
        for (ItemPedidoJpa itemJpa : itensJpa) {
            ItemPedido item = adaptarParaEntidadeNegocio(itemJpa);
            result.add(item);
        }
        return result;
    }

}
