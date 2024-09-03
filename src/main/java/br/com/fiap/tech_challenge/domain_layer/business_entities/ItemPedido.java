package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.math.BigDecimal;

import br.com.fiap.tech_challenge.domain_layer.exceptions.ItemPedidoExceptions;

public class ItemPedido {

    private final Produto produto;
    private final int quantidade;

    // Construtor
    public ItemPedido(Produto produto, int quantidade) throws Exception {
        try {
            validarCpf(produto, quantidade);
            this.produto = produto;
            this.quantidade = quantidade;
        } catch (Exception e) {
            String msg = "Erro ao instanciar o item de pedido! " + e.getMessage();
            throw new Exception(msg);
        }
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
    private void validarCpf(Produto produto, int quantidade) throws Exception {
        validarProduto(produto);
        validarQuantidade(quantidade);
    }

    private void validarProduto(Produto produto) throws Exception {
        if (produto == null) {
            throw new Exception(ItemPedidoExceptions.PRODUTO_NULO.getMensagem());
        }
    }

    private void validarQuantidade(int quantidade) throws Exception {
        if (quantidade < 0) {
            throw new Exception(ItemPedidoExceptions.QTDE_MIN.getMensagem());
        } else if (quantidade > 50) {
            throw new Exception(ItemPedidoExceptions.QTDE_MAX.getMensagem());
        }
    }
}
