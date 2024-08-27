package br.com.fiap.tech_challenge.domain_layer.exceptions;

public enum PedidoExceptionsMsg {
    ID_MIN("O ID do produto deve ser maior que 0."),
    DATA_NULA("A data do pedido não pode ser nula."),
    DATA_MIN("A data do pedido não pode ser menor que 01/01/2024."),
    DATA_MAX("A data do pedido não pode ser maior que a data atual."),
    DATA_CHECKOUT_MIN("A data do checkout não pode ser menor que 01/01/2024."),
    DATA_CHECKOUT_MAX("A data do checkout não pode ser maior que a data atual."),
    STATUS_NULO("O status do pedido não pode ser nulo."),
    DATA_STATUS_NULA("A data do status do pedido não pode ser nula."),
    DATA_STATUS_MIN("A data do status do pedido não pode ser menor que 01/01/2024."),
    DATA_STATUS_MAX("A data do status do pedido não pode ser maior que a data atual."),
    ITEM_NULO("O item não pode ser nulo."),
    NUMERO_ITEM("O número do item é inválido."),
    CHECKOUT_REALIZADO("O checkout do pedido já foi realizado."),
    PEDIDO_VAZIO("Nenhum item foi adicionado ao pedido."),
    PEDIDO_FINALIZADO("O pedido já foi finalizado.");

    private String mensagem;

    PedidoExceptionsMsg(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
