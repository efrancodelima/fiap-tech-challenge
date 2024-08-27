package br.com.fiap.tech_challenge.domain_layer.exceptions;

public class BusinessRulesExceptions extends Exception {
    public BusinessRulesExceptions(String mensagem) {
        super(mensagem);
    }
}
