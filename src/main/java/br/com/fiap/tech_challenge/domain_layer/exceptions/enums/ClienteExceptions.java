package br.com.fiap.tech_challenge.domain_layer.exceptions.enums;

public enum ClienteExceptions {
    CODIGO_MIN("O código do cliente deve ser maior que 0."),
    NOME_MAX_CHAR("O nome do cliente não pode ter mais de 30 caracteres."),
    NOME_INVALIDO("O nome do cliente deve conter, no mínimo, uma palavra com três ou mais caracteres."),
    CPF_NULO("O CPF não pode ser nulo."),
    EMAIL_MAX_CHAR("O e-mail não pode ter mais de 20 caracteres."),
    EMAIL_INVALIDO("O e-mail informado é inválido."),
    NOME_EMAIL_NULOS("Informe, pelo menos, um dos campos para o cliente: nome ou e-mail.");

    private String mensagem;

    ClienteExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
