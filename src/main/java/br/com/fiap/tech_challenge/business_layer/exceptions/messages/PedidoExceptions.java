package br.com.fiap.tech_challenge.business_layer.exceptions.messages;

import br.com.fiap.tech_challenge.business_layer.constants.Validacao;

public enum PedidoExceptions {
    NUMERO_MIN("O número do pedido deve ser maior que 0."),
    ITENS_VAZIO("O pedido deve conter pelo menos um item."),
    ITENS_APOS_CHECKOUT("Não é possível alterar os itens do pedido após a realização do chekout."),
    DATA_CHECKOUT_NULA("A data do checkout não pode ser nula se o checkout já foi feito."),
    DATA_CHECKOUT_MIN("A data do checkout não pode ser menor que " + Validacao.dataMinimaString + "."),
    DATA_CHECKOUT_MAX("A data/hora do checkout não pode ser maior que a data/hora atual."),
    STATUS_NULO("O status do pedido não pode ser nulo."),
    ITEM_NULO("O item não pode ser nulo."),
    CHECKOUT_REALIZADO("O checkout do pedido já foi realizado."),
    PEDIDO_VAZIO("O pedido precisa conter, pelo menos, um item."),
    PEDIDO_FINALIZADO("O pedido já foi finalizado."),
    PEDIDO_NAO_PAGO("O pedido ainda não teve o pagamento aprovado.");

    private String mensagem;

    PedidoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
