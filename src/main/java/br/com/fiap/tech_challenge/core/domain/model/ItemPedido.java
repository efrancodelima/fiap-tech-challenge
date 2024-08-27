package br.com.fiap.tech_challenge.core.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedido {
    private Long idProduto;
    private String descricao;
    private BigDecimal valorUnitario;
    private int quantidade;
    private BigDecimal valorTotalPedido;
}
