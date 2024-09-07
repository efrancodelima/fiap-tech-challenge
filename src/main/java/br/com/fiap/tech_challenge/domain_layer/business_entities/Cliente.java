package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.util.ArrayList;
import java.util.regex.Pattern;

import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesException;
import br.com.fiap.tech_challenge.domain_layer.exceptions.enums.ClienteExceptions;

public class Cliente {
    private long id;
    private Cpf cpf;
    private String nome;
    private String email;

    // Construtores
    public Cliente(Cpf cpf, String nome, String email) throws Exception {
        try {
            setCpf(cpf);
            setNome(nome);
            setEmail(email);
            validarCliente(nome, email);
        } catch (Exception e) {
            String msg = "Erro ao instanciar o cliente! ";
            throw new BusinessRulesException(msg + e.getMessage());
        }
    }

    public Cliente(long id, Cpf cpf, String nome, String email) throws Exception {
        this(cpf, nome, email);
        try {
            setId(id);
        } catch (Exception e) {
            String msg = "Erro ao instanciar o cliente! ";
            throw new BusinessRulesException(msg + e.getMessage());
        }
    }

    // Getters
    public long getId() {
        return id;
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
    private void setId(long id) throws Exception {
        validarId(id);
        this.id = id;
    }

    private void setCpf(Cpf cpf) throws Exception {
        validarCpf(cpf);
        this.cpf = cpf;
    }

    private void setNome(String nome) throws Exception {
        nome = nome == null ? null : nome.trim();
        validarnome(nome);
        this.nome = nome;
    }

    private void setEmail(String email) throws Exception {
        email = email == null ? null : email.trim();
        validarEmail(email);
        this.email = email;
    }

    // Métodos de validação
    private void validarId(long id) throws Exception {
        if (id < 1) {
            throw new BusinessRulesException(ClienteExceptions.ID_MIN.getMensagem());
        }
    }

    private void validarCpf(Cpf cpf) throws Exception {
        if (cpf == null) {
            throw new BusinessRulesException(ClienteExceptions.CPF_NULO.getMensagem());
        }
    }

    private void validarnome(String nome) throws Exception {
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

    private void validarEmail(String email) throws Exception {
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

    private void validarCliente(String nome, String email) throws Exception {
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