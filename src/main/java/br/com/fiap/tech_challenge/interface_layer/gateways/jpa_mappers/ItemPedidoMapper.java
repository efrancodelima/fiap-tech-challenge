package br.com.fiap.tech_challenge.interface_layer.gateways.jpa_mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.ItemPedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.ItemPedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.ProdutoJpa;

public final class ItemPedidoMapper {

    // Métodos públicos
    public static ItemPedidoJpa entidadeNegocioParaEntidadeJpa(ItemPedido item) {
        ProdutoJpa produtoJpa = ProdutoMapper.entidadeNegocioParaEntidadeJpa(item.getProduto());
        return new ItemPedidoJpa(produtoJpa, item.getQuantidade());
    }

    public static List<ItemPedidoJpa> entidadesNegocioParaEntidadesJpa(List<ItemPedido> itens) {
        List<ItemPedidoJpa> result = new ArrayList<ItemPedidoJpa>();
        for (ItemPedido item : itens) {
            ItemPedidoJpa itemJpa = entidadeNegocioParaEntidadeJpa(item);
            result.add(itemJpa);
        }
        return result;
    }

    public static ItemPedido entidadeJpaParaEntidadeNegocio(ItemPedidoJpa itemJpa) throws BusinessRulesExceptions {
        Produto produto = ProdutoMapper.entidadeJpaParaEntidadeNegocio(itemJpa.getProduto());
        return new ItemPedido(produto, itemJpa.getQuantidade());
    }

    public static List<ItemPedido> entidadesJpaParaEntidadesNegocio(List<ItemPedidoJpa> itensJpa)
            throws BusinessRulesExceptions {
        List<ItemPedido> result = new ArrayList<ItemPedido>();
        for (ItemPedidoJpa itemJpa : itensJpa) {
            ItemPedido item = entidadeJpaParaEntidadeNegocio(itemJpa);
            result.add(item);
        }
        return result;
    }

}
