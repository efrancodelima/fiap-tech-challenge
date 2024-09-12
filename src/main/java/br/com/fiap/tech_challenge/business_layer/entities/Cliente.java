package br.com.fiap.tech_challenge.business_layer.entities;

import java.util.ArrayList;
import java.util.regex.Pattern;

import br.com.fiap.tech_challenge.business_layer.exceptions.BusinessRuleException;
import br.com.fiap.tech_challenge.business_layer.exceptions.messages.ClienteExceptions;

public class Cliente {
    private long codigo;
    private Cpf cpf;
    private String nome;
    private String email;

    // Construtor usado para criar um cliente novo
    public Cliente(Cpf cpf, String nome, String email) throws BusinessRuleException {
        setCpf(cpf);
        setNome(nome);
        setEmail(email);
        validarCliente(nome, email);
    }

    // Construtor usado para instanciar um cliente do banco de dados
    public Cliente(Long codigo, Cpf cpf, String nome, String email) throws BusinessRuleException {
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
    private void setCodigo(Long codigo) throws BusinessRuleException {
        validarCodigo(codigo);
        this.codigo = codigo;
    }

    private void setCpf(Cpf cpf) throws BusinessRuleException {
        validarCpf(cpf);
        this.cpf = cpf;
    }

    private void setNome(String nome) throws BusinessRuleException {
        nome = nome == null ? null : nome.trim();
        validarnome(nome);
        this.nome = nome;
    }

    private void setEmail(String email) throws BusinessRuleException {
        email = email == null ? null : email.trim();
        validarEmail(email);
        this.email = email;
    }

    // Métodos de validação
    private void validarCodigo(Long codigo) throws BusinessRuleException {
        if (codigo == null) {
            throw new BusinessRuleException(ClienteExceptions.CODIGO_NULO.getMensagem());
        }
        if (codigo < 1) {
            throw new BusinessRuleException(ClienteExceptions.CODIGO_MIN.getMensagem());
        }
    }

    private void validarCpf(Cpf cpf) throws BusinessRuleException {
        if (cpf == null) {
            throw new BusinessRuleException(ClienteExceptions.CPF_NULO.getMensagem());
        }
    }

    private void validarnome(String nome) throws BusinessRuleException {
        if (nome == null || nome.isEmpty()) {
            return;
        } else if (nome.length() > 50) {
            throw new BusinessRuleException(ClienteExceptions.NOME_MAX_CHAR.getMensagem());
        } else {
            ArrayList<String> palavras = getListaPalavras(nome, 3);
            if (palavras.size() < 1) {
                throw new BusinessRuleException(ClienteExceptions.NOME_INVALIDO.getMensagem());
            }
        }
    }

    private void validarEmail(String email) throws BusinessRuleException {
        if (email == null) {
            return;
        } else if (email.length() > 40) {
            throw new BusinessRuleException(ClienteExceptions.EMAIL_MAX_CHAR.getMensagem());
        } else {
            String emailRegexRFC5322 = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern = Pattern.compile(emailRegexRFC5322);
            if (!pattern.matcher(email).matches()) {
                throw new BusinessRuleException(ClienteExceptions.EMAIL_INVALIDO.getMensagem());
            }
        }
    }

    private void validarCliente(String nome, String email) throws BusinessRuleException {
        if (nome == null && email == null) {
            throw new BusinessRuleException(ClienteExceptions.NOME_EMAIL_NULOS.getMensagem());
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