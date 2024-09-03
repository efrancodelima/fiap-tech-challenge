package br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Cliente")
public class ClienteJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private long cpf;

    private String nome;
    private String email;

    // Construtor com todos os argumentos, exceto 'id'
    public ClienteJpa(long cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }
    
    // Construtor com todos os argumentos
    public ClienteJpa(long id, long cpf, String nome, String email) {
        this(cpf, nome, email);
        this.id = id;
    }

}
