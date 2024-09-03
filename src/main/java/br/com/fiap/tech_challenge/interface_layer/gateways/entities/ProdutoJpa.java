package br.com.fiap.tech_challenge.interface_layer.gateways.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigDecimal;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;

@Data
@Entity
@Table(name = "produto")
public class ProdutoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaProduto categoria;

    // Construtor com todos os parâmetros, exceto o id
    public ProdutoJpa(String nome, String descricao, BigDecimal preco, CategoriaProduto categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    // Construtor com todos os parâmetros
    public ProdutoJpa(long id, String nome, String descricao, BigDecimal preco, CategoriaProduto categoria) {
        this(nome, descricao, preco, categoria);
        this.id = id;
    }

}
