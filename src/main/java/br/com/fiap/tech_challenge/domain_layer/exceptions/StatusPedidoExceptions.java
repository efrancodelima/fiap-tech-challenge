package br.com.fiap.tech_challenge.domain_layer.exceptions;

public enum StatusPedidoExceptions {
    STATUS_NULO("O status do pedido não pode ser nulo."),
    DATA_HORA_NULO("A data e a hora do pedido não podem ser nulas."),
    DATA_HORA_MIN("A data do pedido não pode ser anterior a 01/01/2020."),
    DATA_HORA_MAX("A data e a hora do pedido não pode ser posterior à data e à hora atuais.");

    private String mensagem;

    StatusPedidoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
