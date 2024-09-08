package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.math.BigDecimal;

import br.com.fiap.tech_challenge.domain_layer.exceptions.MyBusinessException;
import br.com.fiap.tech_challenge.domain_layer.exceptions.enums.ItemPedidoExceptions;

public class ItemPedido {

    private final Produto produto;
    private final int quantidade;

    // Construtor
    public ItemPedido(Produto produto, int quantidade) throws MyBusinessException {
        validarCpf(produto, quantidade);
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // Getters
    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorItem() {
        return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

    // Métodos de validação
    private void validarCpf(Produto produto, int quantidade) throws MyBusinessException {
        validarProduto(produto);
        validarQuantidade(quantidade);
    }

    private void validarProduto(Produto produto) throws MyBusinessException {
        if (produto == null) {
            throw new MyBusinessException(ItemPedidoExceptions.PRODUTO_NULO.getMensagem());
        }
    }

    private void validarQuantidade(int quantidade) throws MyBusinessException {
        if (quantidade < 0) {
            throw new MyBusinessException(ItemPedidoExceptions.QTDE_MIN.getMensagem());
        } else if (quantidade > 50) {
            throw new MyBusinessException(ItemPedidoExceptions.QTDE_MAX.getMensagem());
        }
    }
}
