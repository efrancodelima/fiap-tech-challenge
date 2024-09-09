package br.com.fiap.tech_challenge.interface_layer.gateways.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.business_layer.entities.enums.StatusPagamentoEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StatusPagamentoJpa {

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = true)
    private StatusPagamentoEnum status;

    @Column(name = "timestamp_status_pagamento", nullable = true)
    private LocalDateTime dataHora;

}
