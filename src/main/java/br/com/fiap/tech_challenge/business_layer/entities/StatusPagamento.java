package br.com.fiap.tech_challenge.business_layer.entities;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.business_layer.constants.Validacao;
import br.com.fiap.tech_challenge.business_layer.entities.enums.StatusPagamentoEnum;
import br.com.fiap.tech_challenge.business_layer.exceptions.BusinessRuleException;
import br.com.fiap.tech_challenge.business_layer.exceptions.messages.StatusPagamentoExceptions;
import br.com.fiap.tech_challenge.business_layer.exceptions.messages.StatusPedidoExceptions;

public class StatusPagamento {

    // Atributos
    private final StatusPagamentoEnum status;
    private final LocalDateTime dataHora;

    // Construtor
    public StatusPagamento(StatusPagamentoEnum status, LocalDateTime timestamp) throws BusinessRuleException {
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
            throws BusinessRuleException {
        validarStatus(status);
        validarTimestamp(timestamp);
    }

    private void validarStatus(StatusPagamentoEnum status) throws BusinessRuleException {
        if (status == null) {
            throw new BusinessRuleException(StatusPedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarTimestamp(LocalDateTime timestamp) throws BusinessRuleException {
        if (timestamp == null) {
            throw new BusinessRuleException(StatusPagamentoExceptions.DATA_HORA_NULO.getMensagem());
        }

        if (timestamp.toLocalDate().isBefore(Validacao.dataMinima)) {
            throw new BusinessRuleException(StatusPagamentoExceptions.DATA_HORA_MIN.getMensagem());
        }

        if (timestamp.isAfter(LocalDateTime.now())) {
            throw new BusinessRuleException(StatusPagamentoExceptions.DATA_HORA_MAX.getMensagem());
        }
    }
}
