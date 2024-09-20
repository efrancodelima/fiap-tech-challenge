package br.com.fiap.tech_challenge.business_layer.entities.pedido;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.business_layer.constants.Validacao;
import br.com.fiap.tech_challenge.business_layer.exceptions.BusinessRuleException;
import br.com.fiap.tech_challenge.business_layer.exceptions.messages.StatusPedidoExceptions;

public class StatusPedido {

    // Atributos
    private final StatusPedidoEnum status;
    private final LocalDateTime dataHora;

    // Construtor
    public StatusPedido(StatusPedidoEnum status, LocalDateTime dataHora) throws BusinessRuleException {
        validarStatusPedido(status, dataHora);
        this.status = status;
        this.dataHora = dataHora;
    }

    // Getters
    public StatusPedidoEnum getStatus() {
        return status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    // Métodos de validação
    private void validarStatusPedido(StatusPedidoEnum status, LocalDateTime dataHora) throws BusinessRuleException {
        validarStatus(status);
        validarDataHora(dataHora);
    }

    private void validarStatus(StatusPedidoEnum status) throws BusinessRuleException {
        if (status == null) {
            throw new BusinessRuleException(StatusPedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarDataHora(LocalDateTime dataHora) throws BusinessRuleException {
        if (dataHora == null) {
            throw new BusinessRuleException(StatusPedidoExceptions.DATA_HORA_NULO.getMensagem());
        }

        if (dataHora.toLocalDate().isBefore(Validacao.dataMinima)) {
            throw new BusinessRuleException(StatusPedidoExceptions.DATA_HORA_MIN.getMensagem());
        }

        if (dataHora.isAfter(LocalDateTime.now())) {
            throw new BusinessRuleException(StatusPedidoExceptions.DATA_HORA_MAX.getMensagem());
        }
    }
}
