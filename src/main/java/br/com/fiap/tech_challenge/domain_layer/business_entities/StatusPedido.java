package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.domain_layer.exceptions.StatusPedidoExceptions;

public class StatusPedido {

    // Atributos
    private StatusPedidoEnum status;
    private LocalDateTime dataHora;

    // Construtor
    StatusPedido(StatusPedidoEnum status, LocalDateTime dataHora) throws BusinessRulesExceptions {
        try {
            validarStatus(status);
            validarDataHora(dataHora);
            this.status = status;
            this.dataHora = dataHora;
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao definir o status do pedido! ";
            throw new BusinessRulesExceptions(msg + e.getMessage());
        }
    }

    // Getters
    public StatusPedidoEnum getStatus() {
        return status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    // Métodos de validação
    private void validarStatus(StatusPedidoEnum status) throws BusinessRulesExceptions {
        if (status == null) {
            throw new BusinessRulesExceptions(StatusPedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarDataHora(LocalDateTime dataHora) throws BusinessRulesExceptions {
        if (dataHora == null) {
            throw new BusinessRulesExceptions(StatusPedidoExceptions.DATA_HORA_NULO.getMensagem());
        }

        if (dataHora.isBefore(Constantes.dataHoraMinima)) {
            throw new BusinessRulesExceptions(StatusPedidoExceptions.DATA_HORA_MIN.getMensagem());
        }

        if (dataHora.isAfter(LocalDateTime.now())) {
            throw new BusinessRulesExceptions(StatusPedidoExceptions.DATA_HORA_MAX.getMensagem());
        }
    }
}
