package br.com.fiap.tech_challenge.core.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;

@Data
public class Pedido {
    private String id;
    private Date dataPedido;
    private BigDecimal valorTotalPedido;
    private StatusPedidoEnum situacaoPedido;
    private List<ItemPedido> itens = new ArrayList<>();
}
