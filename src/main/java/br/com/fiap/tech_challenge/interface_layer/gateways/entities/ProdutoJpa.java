package br.com.fiap.tech_challenge.interface_layer.gateways.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import br.com.fiap.tech_challenge.business_layer.entities.produto.CategoriaProduto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class ProdutoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "nome", nullable = false, columnDefinition = "VARCHAR(20)")
    private String nome;

    @Column(name = "descricao", columnDefinition = "VARCHAR(150)")
    private String descricao;

    @Column(name = "preco", nullable = false, columnDefinition = "DECIMAL(5,2)")
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaProduto categoria;

}
