package br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.math.BigDecimal;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;

@Entity
@Data
@Table(name = "PRODUTO")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produtoId;

    @Column(name = "nome", nullable = false, length = 50)
    @NotBlank(message = "Informe o nome do produto")
    private String nome;

    @Column(name = "descricao", length = 200)
    private String descricao;

    @Column(name = "preco", nullable = false)
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaProduto categoriaProduto;
}

