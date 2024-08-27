package br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ITEM_PEDIDO")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "Nome do produto")
    private String nome;

    @Column(name = "descricao", nullable = false, length = 100)
    @NotBlank(message = "Descrição do produto")
    private String descricao;

    @Column(name = "valor_unitario", nullable = false)
    @DecimalMin(value = "0.01", message = "O valor unitário deve ser maior que zero")
    private BigDecimal valorUnitario;

    @Column(name = "quantidade", nullable = false)
    @DecimalMin(value = "1", message = "A quantidade deve ser maior que zero")
    private int quantidade;

    @Column(name = "valor_total_item", nullable = false)
    @DecimalMin(value = "0.01", message = "O valor total deve ser maior que zero")
    private BigDecimal valorTotalPedido;

    @ManyToOne
    private PedidoEntity pedido;

}
