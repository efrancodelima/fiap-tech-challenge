package br.com.fiap.tech_challenge.interface_layer.controllers.adapters.request_adapters;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tech_challenge.business_layer.entities.ItemPedido;
import br.com.fiap.tech_challenge.business_layer.entities.Produto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.ItemPedidoDto;

public final class ItemPedidoRequestAdapter {

    public static List<ItemPedido> adaptar(List<ItemPedidoDto> itens, List<Produto> produtos)
            throws Exception {
        List<ItemPedido> result = new ArrayList<>();

        for (int i = 0; i < itens.size(); i++) {
            ItemPedido item = new ItemPedido(produtos.get(i), itens.get(i).getQuantidade());
            result.add(item);
        }
        return result;
    }
}
