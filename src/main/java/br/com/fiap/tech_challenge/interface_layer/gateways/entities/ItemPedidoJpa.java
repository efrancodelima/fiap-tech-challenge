package br.com.fiap.tech_challenge.interface_layer.gateways.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class ItemPedidoJpa implements Serializable {

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoJpa produto;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    // Construtor
    public ItemPedidoJpa(ProdutoJpa produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

}
