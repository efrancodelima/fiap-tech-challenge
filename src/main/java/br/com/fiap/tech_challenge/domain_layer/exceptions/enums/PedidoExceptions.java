package br.com.fiap.tech_challenge.domain_layer.exceptions.enums;

public enum PedidoExceptions {
    NUMERO_MIN("O número do pedido deve ser maior que 0."),
    ITENS_VAZIO("O pedido deve conter pelo menos um item."),
    ITENS_APOS_CHECKOUT("Não é possível alterar os itens do pedido após a realização do chekout."),
    DATA_NULA("A data do pedido não pode ser nula."),
    DATA_MIN("A data do pedido não pode ser menor que 01/01/2024."),
    DATA_MAX("A data do pedido não pode ser maior que a data atual."),
    DATA_CHECKOUT_NULA("A data do checkout não pode ser nula se o checkout já foi feito."),
    DATA_CHECKOUT_MIN("A data do checkout não pode ser menor que 01/01/2024."),
    DATA_CHECKOUT_MAX("A data do checkout não pode ser maior que a data atual."),
    STATUS_NULO("O status do pedido não pode ser nulo."),
    ITEM_NULO("O item não pode ser nulo."),
    NUMERO_ITEM("O número do item é inválido."),
    CHECKOUT_REALIZADO("O checkout do pedido já foi realizado."),
    PEDIDO_VAZIO("Nenhum item foi adicionado ao pedido."),
    PEDIDO_FINALIZADO("O pedido já foi finalizado.");

    private String mensagem;

    PedidoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
