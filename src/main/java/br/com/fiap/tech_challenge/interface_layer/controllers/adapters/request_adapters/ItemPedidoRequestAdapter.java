package br.com.fiap.tech_challenge.interface_layer.controllers.adapters.request_adapters;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tech_challenge.application_layer.use_cases.ProdutoUseCase;
import br.com.fiap.tech_challenge.business_layer.entities.ItemPedido;
import br.com.fiap.tech_challenge.business_layer.entities.Produto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.ItemPedidoDto;

public final class ItemPedidoRequestAdapter {

    // Métodos públicos
    public static ItemPedido adaptar(ProdutoUseCase produtoUseCase, ItemPedidoDto itemPedidoDto) throws Exception {
        Produto produto = produtoUseCase.buscarProduto(itemPedidoDto.getCodigoProduto());
        return new ItemPedido(produto, itemPedidoDto.getQuantidade());
    }

    public static List<ItemPedido> adaptar(ProdutoUseCase produtoUseCase, List<ItemPedidoDto> itensPedidoDto)
            throws Exception {

        List<ItemPedido> result = new ArrayList<>();

        for (ItemPedidoDto itemDto : itensPedidoDto) {
            ItemPedido item = ItemPedidoRequestAdapter.adaptar(produtoUseCase, itemDto);
            result.add(item);
        }
        return result;
    }
}
