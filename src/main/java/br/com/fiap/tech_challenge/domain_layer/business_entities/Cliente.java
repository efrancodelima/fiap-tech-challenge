package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.util.ArrayList;
import java.util.regex.Pattern;

import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.domain_layer.exceptions.ClienteExceptions;

public class Cliente {
    private long id;
    private String nome;
    private Cpf cpf;
    private String email;

    // Construtores
    public Cliente(String nome, Cpf cpf, String email) throws BusinessRulesExceptions {
        try {
            setNome(nome);
            this.cpf = cpf;
            setEmail(email);
            validarCliente();
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao instanciar o cliente! ";
            throw new BusinessRulesExceptions(msg + e.getMessage());
        }
    }

    public Cliente(long id, String nome, Cpf cpf, String email) throws BusinessRulesExceptions {
        this(nome, cpf, email);
        try {
            setId(id);
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao instanciar o cliente! ";
            throw new BusinessRulesExceptions(msg + e.getMessage());
        }
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    private void setId(long id) throws BusinessRulesExceptions {
        validarId(id);
        this.id = id;
    }

    private void setNome(String nome) throws BusinessRulesExceptions {
        nome = nome == null ? null : nome.trim();
        validarnome(nome);
        this.nome = nome;
    }

    public void setEmail(String email) throws BusinessRulesExceptions {
        email = email == null ? null : email.trim();
        validarEmail(email);
        this.email = email;
    }

    // Métodos de validação
    private void validarId(long id) throws BusinessRulesExceptions {
        if (id < 1) {
            throw new BusinessRulesExceptions(ClienteExceptions.ID_MIN.getMensagem());
        }
    }

    private void validarnome(String nome) throws BusinessRulesExceptions {
        if (nome == null || nome.isEmpty()) {
            return;
        } else if (nome.length() > 30) {
            throw new BusinessRulesExceptions(ClienteExceptions.NOME_MAX_CHAR.getMensagem());
        } else {
            ArrayList<String> palavras = getListaPalavras(nome, 3);
            if (palavras.size() < 1) {
                throw new BusinessRulesExceptions(ClienteExceptions.NOME_INVALIDO.getMensagem());
            }
        }
    }

    private void validarEmail(String email) throws BusinessRulesExceptions {
        if (email == null) {
            return;
        } else if (email.length() > 20) {
            throw new BusinessRulesExceptions(ClienteExceptions.EMAIL_MAX_CHAR.getMensagem());
        } else {
            String emailRegexRFC5322 = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern = Pattern.compile(emailRegexRFC5322);
            if (!pattern.matcher(email).matches()) {
                throw new BusinessRulesExceptions(ClienteExceptions.EMAIL_INVALIDO.getMensagem());
            }
        }
    }

    public void validarCliente() throws BusinessRulesExceptions {
        if (nome == null && cpf == null && email == null) {
            throw new BusinessRulesExceptions(ClienteExceptions.CLIENTE_INVALIDO.getMensagem());
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