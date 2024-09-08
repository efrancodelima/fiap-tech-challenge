package br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tech_challenge.application_layer.use_cases.ProdutoUseCase;
import br.com.fiap.tech_challenge.domain_layer.business_entities.ItemPedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.interface_layer.dtos.ItemPedidoDto;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.ResourceNotFoundException;

public final class ItemPedidoRequestAdapter {

    // Métodos públicos
    public static ItemPedido adaptar(ProdutoUseCase produtoUseCase, ItemPedidoDto itemPedidoDto)
            throws ResourceNotFoundException, Exception {
        Produto produto = produtoUseCase.buscarProdutoPorCodigo(itemPedidoDto.getCodigoProduto());
        return new ItemPedido(produto, itemPedidoDto.getQuantidade());
    }

    public static List<ItemPedido> adaptar(ProdutoUseCase produtoUseCase, List<ItemPedidoDto> itensPedidoDto)
            throws ResourceNotFoundException, Exception {

        List<ItemPedido> result = new ArrayList<>();

        for (ItemPedidoDto itemDto : itensPedidoDto) {
            ItemPedido item = ItemPedidoRequestAdapter.adaptar(produtoUseCase, itemDto);
            result.add(item);
        }
        return result;
    }
}
