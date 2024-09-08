package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPagamentoEnum;
import br.com.fiap.tech_challenge.domain_layer.constants.Validacao;
import br.com.fiap.tech_challenge.domain_layer.exceptions.MyBusinessException;
import br.com.fiap.tech_challenge.domain_layer.exceptions.enums.StatusPagamentoExceptions;
import br.com.fiap.tech_challenge.domain_layer.exceptions.enums.StatusPedidoExceptions;

public class StatusPagamento {

    // Atributos
    private final StatusPagamentoEnum status;
    private final LocalDateTime dataHora;

    // Construtor
    public StatusPagamento(StatusPagamentoEnum status, LocalDateTime timestamp) throws MyBusinessException {
        validarStatusPagamento(status, timestamp);
        this.status = status;
        this.dataHora = timestamp;
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
            throws MyBusinessException {
        validarStatus(status);
        validarTimestamp(timestamp);
    }

    private void validarStatus(StatusPagamentoEnum status) throws MyBusinessException {
        if (status == null) {
            throw new MyBusinessException(StatusPedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarTimestamp(LocalDateTime timestamp) throws MyBusinessException {
        if (timestamp == null) {
            throw new MyBusinessException(StatusPagamentoExceptions.DATA_HORA_NULO.getMensagem());
        }

        if (timestamp.isBefore(Validacao.dataHoraMinima)) {
            throw new MyBusinessException(StatusPagamentoExceptions.DATA_HORA_MIN.getMensagem());
        }

        if (timestamp.isAfter(LocalDateTime.now())) {
            throw new MyBusinessException(StatusPagamentoExceptions.DATA_HORA_MAX.getMensagem());
        }
    }
}
