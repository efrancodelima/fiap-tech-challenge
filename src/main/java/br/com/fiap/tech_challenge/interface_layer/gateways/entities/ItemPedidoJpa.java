package br.com.fiap.tech_challenge.interface_layer.gateways.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ItemPedidoJpa implements Serializable {

    @ManyToOne
    @JoinColumn(name = "codigo_produto", nullable = false)
    private ProdutoJpa produtoJpa;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

}
