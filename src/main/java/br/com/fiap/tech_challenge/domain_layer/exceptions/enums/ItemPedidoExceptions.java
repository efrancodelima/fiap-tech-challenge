package br.com.fiap.tech_challenge.domain_layer.exceptions.enums;

public enum ItemPedidoExceptions {
    PRODUTO_NULO("O produto não pode ser nulo."),
    QTDE_MIN("A quantidade deve ser maior que zero."),
    QTDE_MAX("A quantidade máxima permitida é 50.");

    private String mensagem;

    ItemPedidoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
