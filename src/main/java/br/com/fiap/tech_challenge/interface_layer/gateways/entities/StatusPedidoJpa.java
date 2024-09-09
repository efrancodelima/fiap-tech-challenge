package br.com.fiap.tech_challenge.interface_layer.gateways.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.business_layer.entities.enums.StatusPedidoEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StatusPedidoJpa {

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido", nullable = false)
    private StatusPedidoEnum status;

    @Column(name = "timestamp_status_pedido", nullable = false)
    private LocalDateTime dataHora;

}
