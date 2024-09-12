package br.com.fiap.tech_challenge.interface_layer.gateways.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class PedidoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    @ManyToOne
    @JoinColumn(name = "codigo_cliente", nullable = true)
    private ClienteJpa clienteJpa;

    @ElementCollection
    @CollectionTable(name = "itens_pedido", joinColumns = @JoinColumn(name = "numero_pedido"))
    private List<ItemPedidoJpa> itensJpa;

    @Column(name = "timestamp_checkout", nullable = true)
    private LocalDateTime dataHoraCheckout;

    @Embedded
    private StatusPagamentoJpa statusPagamento;

    @Embedded
    private StatusPedidoJpa statusPedido;

}
