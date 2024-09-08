package br.com.fiap.tech_challenge.domain_layer.exceptions.enums;

public enum CpfExceptions {
    NUMERO_MIN("O número do CPF deve ser maior que 0 (sem considerar os dígitos verificadores)."),
    NUMERO_MAX("O número do CPF não pode ter mais de 9 dígitos."),
    DIGITO_INVALIDO("O dígito verificador do CPF é inválido.");

    private String mensagem;

    CpfExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
