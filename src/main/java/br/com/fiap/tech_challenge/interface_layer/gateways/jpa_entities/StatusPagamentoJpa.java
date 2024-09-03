package br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPagamentoEnum;

@Data
@Embeddable
public class StatusPagamentoJpa {

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = true)
    private StatusPagamentoEnum status;

    @Column(name = "timestamp_status_pagamento", nullable = true)
    private LocalDateTime dataHora;

    // Construtor
    public StatusPagamentoJpa(StatusPagamentoEnum status, LocalDateTime dataHora) {
        this.status = status;
        this.dataHora = dataHora;
    }

}
