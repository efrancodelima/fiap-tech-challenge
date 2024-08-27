package br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "CLIENTE")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    @Size(min = 11, max = 11, message = "O cpf deve ter 11 caracteres")
    private String cpf;

    @Column(name = "nome_completo", nullable = false, length = 120)
    @NotBlank(message = "Informe o nome completo")
    private String nomeCompleto;

    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<TelefoneEntity> telefones = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<EnderecoEntity> enderecos = new ArrayList<>();

}
