package br.com.fiap.tech_challenge.interface_layer.controllers.dtos;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

    @Schema(description = "Nome do produto.", example = "X-Monstrão")
    public String nome;

    @Schema(description = "Descrição do produto (opcional).", example = "O lanche do marombeiro, repleto de whey e creatina.")
    public String descricao;

    @Schema(description = "Preço do produto. Use vírgula no lugar de ponto.", example = "35.90")
    public BigDecimal preco;

    @Schema(description = "Categoria do produto. Valores possíveis: lanche, acompanhamento, bebida ou sobremesa.", example = "lanche")
    public String categoria;
}
