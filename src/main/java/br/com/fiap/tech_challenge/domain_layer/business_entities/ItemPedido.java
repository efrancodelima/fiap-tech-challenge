package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.math.BigDecimal;

import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.domain_layer.exceptions.ItemPedidoExceptions;

public class ItemPedido {

    private Produto produto;
    private int quantidade;

    // Construtor
    ItemPedido(Produto produto, int quantidade) throws BusinessRulesExceptions {
        try {
            setProduto(produto);
            setQuantidade(quantidade);
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao instanciar o item de pedido! " + e.getMessage();
            throw new BusinessRulesExceptions(msg);
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

    // Setters
    private void setProduto(Produto produto) throws BusinessRulesExceptions {
        validarProduto(produto);
        this.produto = produto;
    }

    private void setQuantidade(int quantidade) throws BusinessRulesExceptions {
        validarQuantidade(quantidade);
        this.quantidade = quantidade;
    }

    // Métodos de validação
    private void validarProduto(Produto produto) throws BusinessRulesExceptions {
        if (produto == null) {
            throw new BusinessRulesExceptions(ItemPedidoExceptions.PRODUTO_NULO.getMensagem());
        }
    }

    private void validarQuantidade(int quantidade) throws BusinessRulesExceptions {
        if (quantidade < 0) {
            throw new BusinessRulesExceptions(ItemPedidoExceptions.QTDE_MIN.getMensagem());
        } else if (quantidade > 50) {
            throw new BusinessRulesExceptions(ItemPedidoExceptions.QTDE_MAX.getMensagem());
        }
    }
}
