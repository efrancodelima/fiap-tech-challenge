package br.com.fiap.tech_challenge.business_layer.entities.pedido;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.business_layer.constants.Validacao;
import br.com.fiap.tech_challenge.business_layer.exceptions.BusinessRuleException;
import br.com.fiap.tech_challenge.business_layer.exceptions.messages.StatusPagamentoExceptions;

public class StatusPagamento {

    // Atributos
    private Long codigo;
    private StatusPagamentoEnum status;
    private LocalDateTime dataHora;

    // Construtores
    public StatusPagamento(Long codigo, StatusPagamentoEnum status, LocalDateTime timestamp)
            throws BusinessRuleException {
        validarCodigo(codigo);
        validarStatusPagamento(status, timestamp);
        this.codigo = codigo;
        this.status = status;
        this.dataHora = timestamp;
    }

    // Getters e setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) throws BusinessRuleException {
        if (this.codigo != null) {
            throw new BusinessRuleException(StatusPagamentoExceptions.CODIGO_ALTERADO.getMensagem());
        } else {
            validarCodigo(codigo);
            this.codigo = codigo;
        }
    }

    public StatusPagamentoEnum getStatus() {
        return status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    // Métodos de validação
    private void validarCodigo(Long codigo) throws BusinessRuleException {
        if (codigo != null && codigo < 1) {
            throw new BusinessRuleException(StatusPagamentoExceptions.CODIGO_MIN.getMensagem());
        }
    }

    private void validarStatusPagamento(StatusPagamentoEnum status, LocalDateTime timestamp)
            throws BusinessRuleException {
        validarStatus(status);
        validarTimestamp(timestamp);
    }

    private void validarStatus(StatusPagamentoEnum status) throws BusinessRuleException {
        if (status == null) {
            throw new BusinessRuleException(StatusPagamentoExceptions.STATUS_NULO.getMensagem());
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
