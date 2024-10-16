package br.com.fiap.tech_challenge.business_layer.exceptions.messages;

import br.com.fiap.tech_challenge.business_layer.constants.Validacao;

public enum StatusPedidoExceptions {
    STATUS_NULO("O status do pedido não pode ser nulo."),
    DATA_HORA_NULO("A data/hora do pedido não pode ser nula."),
    DATA_HORA_MIN("A data do pedido não pode ser anterior a " + Validacao.dataMinimaString + "."),
    DATA_HORA_MAX("A data/hora do pedido não pode ser maior que a data/hora atual.");

    private String mensagem;

    StatusPedidoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
