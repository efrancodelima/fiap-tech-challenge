package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;
import br.com.fiap.tech_challenge.domain_layer.constants.Validacao;
import br.com.fiap.tech_challenge.domain_layer.exceptions.enums.StatusPedidoExceptions;

public class StatusPedido {

    // Atributos
    private final StatusPedidoEnum status;
    private final LocalDateTime dataHora;

    // Construtor
    public StatusPedido(StatusPedidoEnum status, LocalDateTime dataHora) throws Exception {
        try {
            validarStatusPedido(status, dataHora);
            this.status = status;
            this.dataHora = dataHora;
        } catch (Exception e) {
            String msg = "Erro ao definir o status do pedido! ";
            throw new Exception(msg + e.getMessage());
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
    private void validarStatusPedido(StatusPedidoEnum status, LocalDateTime dataHora) throws Exception {
        validarStatus(status);
        validarDataHora(dataHora);
    }

    private void validarStatus(StatusPedidoEnum status) throws Exception {
        if (status == null) {
            throw new Exception(StatusPedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarDataHora(LocalDateTime dataHora) throws Exception {
        if (dataHora == null) {
            throw new Exception(StatusPedidoExceptions.DATA_HORA_NULO.getMensagem());
        }

        if (dataHora.isBefore(Validacao.dataHoraMinima)) {
            throw new Exception(StatusPedidoExceptions.DATA_HORA_MIN.getMensagem());
        }

        if (dataHora.isAfter(LocalDateTime.now())) {
            throw new Exception(StatusPedidoExceptions.DATA_HORA_MAX.getMensagem());
        }
    }
}
