package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.util.ArrayList;
import java.util.regex.Pattern;

import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesException;
import br.com.fiap.tech_challenge.domain_layer.exceptions.enums.ClienteExceptions;

public class Cliente {
    private long codigo;
    private Cpf cpf;
    private String nome;
    private String email;

    // Construtores
    public Cliente(Cpf cpf, String nome, String email) throws BusinessRulesException {
        setCpf(cpf);
        setNome(nome);
        setEmail(email);
        validarCliente(nome, email);
    }

    public Cliente(long codigo, Cpf cpf, String nome, String email) throws BusinessRulesException {
        this(cpf, nome, email);
        setCodigo(codigo);
    }

    // Getters
    public long getCodigo() {
        return codigo;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    private void setCodigo(long codigo) throws BusinessRulesException {
        validarCodigo(codigo);
        this.codigo = codigo;
    }

    private void setCpf(Cpf cpf) throws BusinessRulesException {
        validarCpf(cpf);
        this.cpf = cpf;
    }

    private void setNome(String nome) throws BusinessRulesException {
        nome = nome == null ? null : nome.trim();
        validarnome(nome);
        this.nome = nome;
    }

    private void setEmail(String email) throws BusinessRulesException {
        email = email == null ? null : email.trim();
        validarEmail(email);
        this.email = email;
    }

    // Métodos de validação
    private void validarCodigo(long codigo) throws BusinessRulesException {
        if (codigo < 1) {
            throw new BusinessRulesException(ClienteExceptions.CODIGO_MIN.getMensagem());
        }
    }

    private void validarCpf(Cpf cpf) throws BusinessRulesException {
        if (cpf == null) {
            throw new BusinessRulesException(ClienteExceptions.CPF_NULO.getMensagem());
        }
    }

    private void validarnome(String nome) throws BusinessRulesException {
        if (nome == null || nome.isEmpty()) {
            return;
        } else if (nome.length() > 30) {
            throw new BusinessRulesException(ClienteExceptions.NOME_MAX_CHAR.getMensagem());
        } else {
            ArrayList<String> palavras = getListaPalavras(nome, 3);
            if (palavras.size() < 1) {
                throw new BusinessRulesException(ClienteExceptions.NOME_INVALIDO.getMensagem());
            }
        }
    }

    private void validarEmail(String email) throws BusinessRulesException {
        if (email == null) {
            return;
        } else if (email.length() > 20) {
            throw new BusinessRulesException(ClienteExceptions.EMAIL_MAX_CHAR.getMensagem());
        } else {
            String emailRegexRFC5322 = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern = Pattern.compile(emailRegexRFC5322);
            if (!pattern.matcher(email).matches()) {
                throw new BusinessRulesException(ClienteExceptions.EMAIL_INVALIDO.getMensagem());
            }
        }
    }

    private void validarCliente(String nome, String email) throws BusinessRulesException {
        if (nome == null && email == null) {
            throw new BusinessRulesException(ClienteExceptions.NOME_EMAIL_NULOS.getMensagem());
        }
    }

    // Métodos auxiliares
    private ArrayList<String> getListaPalavras(String texto, int minChar) {
        String[] palavras = texto.split(" ");
        ArrayList<String> palavrasValidas = new ArrayList<>();

        for (String palavra : palavras) {
            if (palavra.length() >= minChar) {
                palavrasValidas.add(palavra);
            }
        }
        return palavrasValidas;
    }
}