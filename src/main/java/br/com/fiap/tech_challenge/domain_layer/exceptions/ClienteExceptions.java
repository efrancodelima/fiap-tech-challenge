package br.com.fiap.tech_challenge.domain_layer.exceptions;

public enum ClienteExceptions {
    ID_MIN("O ID deve ser maior que 0."),
    NOME_MAX_CHAR("O nome não pode ter mais de 30 caracteres."),
    NOME_INVALIDO("O nome deve conter, no mínimo, uma palavra com três ou mais caracteres."),
    CPF_NULO("O CPF não pode ser nulo."),
    EMAIL_MAX_CHAR("O e-mail não pode ter mais de 20 caracteres."),
    EMAIL_INVALIDO("O e-mail informado é inválido."),
    NOME_EMAIL_NULOS("Informe, pelo menos, um dos campos: nome ou e-mail.");

    private String mensagem;

    ClienteExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
