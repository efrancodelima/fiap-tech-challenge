package br.com.fiap.tech_challenge.interface_layer.gateways.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class PedidoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true)
    private ClienteJpa cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedidoJpa> itens;

    @Column(name = "timestamp_checkout", nullable = true)
    private LocalDateTime dataHoraCheckout;

    @Embedded
    private StatusPagamentoJpa statusPagamento;

    @Embedded
    private StatusPedidoJpa statusPedido;

    // Construtor
    public PedidoJpa(long id, ClienteJpa cliente, List<ItemPedidoJpa> itens, LocalDateTime dataHoraCheckout,
            StatusPagamentoJpa statusPagamento, StatusPedidoJpa statusPedido) {
        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
        this.dataHoraCheckout = dataHoraCheckout;
        this.statusPagamento = statusPagamento;
        this.statusPedido = statusPedido;
    }

}
