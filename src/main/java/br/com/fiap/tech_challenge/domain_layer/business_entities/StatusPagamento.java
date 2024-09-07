package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPagamentoEnum;
import br.com.fiap.tech_challenge.domain_layer.exceptions.enums.StatusPagamentoExceptions;
import br.com.fiap.tech_challenge.domain_layer.exceptions.enums.StatusPedidoExceptions;

public class StatusPagamento {

    // Atributos
    private final StatusPagamentoEnum status;
    private final LocalDateTime dataHora;

    // Construtor
    public StatusPagamento(StatusPagamentoEnum status, LocalDateTime timestamp) throws Exception {
        try {
            validarStatusPagamento(status, timestamp);
            this.status = status;
            this.dataHora = timestamp;
        } catch (Exception e) {
            String msg = "Erro ao definir o status do pagamento! ";
            throw new Exception(msg + e.getMessage());
        }
    }

    // Getters
    public StatusPagamentoEnum getStatus() {
        return status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    // Métodos de validação
    private void validarStatusPagamento(StatusPagamentoEnum status, LocalDateTime timestamp)
            throws Exception {
        validarStatus(status);
        validarTimestamp(timestamp);
    }

    private void validarStatus(StatusPagamentoEnum status) throws Exception {
        if (status == null) {
            throw new Exception(StatusPedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarTimestamp(LocalDateTime timestamp) throws Exception {
        if (timestamp == null) {
            throw new Exception(StatusPagamentoExceptions.DATA_HORA_NULO.getMensagem());
        }

        if (timestamp.isBefore(Constantes.dataHoraMinimaValidacao)) {
            throw new Exception(StatusPagamentoExceptions.DATA_HORA_MIN.getMensagem());
        }

        if (timestamp.isAfter(LocalDateTime.now())) {
            throw new Exception(StatusPagamentoExceptions.DATA_HORA_MAX.getMensagem());
        }
    }
}
