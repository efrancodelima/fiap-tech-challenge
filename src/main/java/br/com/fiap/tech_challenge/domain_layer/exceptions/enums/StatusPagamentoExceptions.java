package br.com.fiap.tech_challenge.domain_layer.exceptions.enums;

public enum StatusPagamentoExceptions {
    STATUS_NULO("O status do pagamento não pode ser nulo."),
    DATA_HORA_NULO("A data e a hora do pagamento não podem ser nulas."),
    DATA_HORA_MIN("A data do pagamento não pode ser anterior a 01/01/2020."),
    DATA_HORA_MAX("A data e a hora do pagamento não pode ser posterior à data e à hora atuais.");

    private String mensagem;

    StatusPagamentoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
