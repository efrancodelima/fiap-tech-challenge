package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPagamentoEnum;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.domain_layer.exceptions.StatusPagamentoExceptions;
import br.com.fiap.tech_challenge.domain_layer.exceptions.StatusPedidoExceptions;

public class StatusPagamento {

    // Atributos
    private final StatusPagamentoEnum status;
    private final LocalDateTime dataHora;

    // Construtor
    StatusPagamento(StatusPagamentoEnum status, LocalDateTime timestamp) throws BusinessRulesExceptions {
        try {
            validarStatusPagamento(status, timestamp);
            this.status = status;
            this.dataHora = timestamp;
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao definir o status do pagamento! ";
            throw new BusinessRulesExceptions(msg + e.getMessage());
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
            throws BusinessRulesExceptions {
        validarStatus(status);
        validarTimestamp(timestamp);
    }

    private void validarStatus(StatusPagamentoEnum status) throws BusinessRulesExceptions {
        if (status == null) {
            throw new BusinessRulesExceptions(StatusPedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarTimestamp(LocalDateTime timestamp) throws BusinessRulesExceptions {
        if (timestamp == null) {
            throw new BusinessRulesExceptions(StatusPagamentoExceptions.DATA_HORA_NULO.getMensagem());
        }

        if (timestamp.isBefore(Constantes.dataHoraMinima)) {
            throw new BusinessRulesExceptions(StatusPagamentoExceptions.DATA_HORA_MIN.getMensagem());
        }

        if (timestamp.isAfter(LocalDateTime.now())) {
            throw new BusinessRulesExceptions(StatusPagamentoExceptions.DATA_HORA_MAX.getMensagem());
        }
    }
}
