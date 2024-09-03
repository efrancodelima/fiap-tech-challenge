package br.com.fiap.tech_challenge.interface_layer.gateways.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;

@Data
@Embeddable
public class StatusPedidoJpa {

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido", nullable = false)
    private StatusPedidoEnum status;

    @Column(name = "timestamp_status_pedido", nullable = false)
    private LocalDateTime dataHora;

    // Construtor
    public StatusPedidoJpa(StatusPedidoEnum status, LocalDateTime dataHora) {
        this.status = status;
        this.dataHora = dataHora;
    }

}
