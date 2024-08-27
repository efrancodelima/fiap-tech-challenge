package br.com.fiap.tech_challenge.core.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Cliente {

    private Long id;
    private String cpf;
    private String nomeCompleto;
    private String email;
    private List<Telefone> telefones;
    private List<Endereco> enderecos;

    public String getCpf() {
        return cpf.replaceAll("[./-]", "");
    }

}
