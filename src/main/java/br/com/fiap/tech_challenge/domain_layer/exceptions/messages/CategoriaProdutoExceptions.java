package br.com.fiap.tech_challenge.domain_layer.exceptions.messages;

public enum CategoriaProdutoExceptions {

    CATEGORIA_INAVLIDA("A categoria do produto é inválida.");

    private String mensagem;

    CategoriaProdutoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

}
