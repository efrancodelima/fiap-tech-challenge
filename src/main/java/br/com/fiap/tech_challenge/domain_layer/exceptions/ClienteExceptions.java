package br.com.fiap.tech_challenge.domain_layer.exceptions;

public enum ClienteExceptions {
    ID_MIN("O ID do cliente deve ser maior que 0."),
    NOME_MAX_CHAR("O nome não pode ter mais de 30 caracteres."),
    NOME_INVALIDO("O nome do cliente deve conter, no mínimo, uma palavra com três ou mais caracteres."),
    EMAIL_MAX_CHAR("O e-mail informado para o cliente é inválido!"),
    EMAIL_INVALIDO("O e-mail informado para o cliente é inválido!"),
    CLIENTE_INVALIDO("Pelo menos um dos campos precisa ser informado:nome, cpf ou e-mail.");

    private String mensagem;

    ClienteExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
