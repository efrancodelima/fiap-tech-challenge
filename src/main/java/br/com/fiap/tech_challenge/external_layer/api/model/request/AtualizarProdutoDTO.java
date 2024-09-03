package br.com.fiap.tech_challenge.external_layer.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.tech_challenge.interface_layer.controller.model.enums.CategoriaProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AtualizarProdutoDTO {

  @Schema(name = "produtoId", description = "Número de identificação do produto", example = "1L")
  private Long produtoId;

  @JsonProperty(required = true)
  @Schema(name = "nome", description = "Nome do produto", example = "X-Tudo")
  private String nome;

  @JsonProperty(required = true)
  @Schema(name = "descricao", description = "Descrição do produto", example = "Lanche com hambúrguer, alface, tomate, cebola, bacon e maionese.")
  private String descricao;

  @JsonProperty(required = true)
  @Schema(name = "preco", description = "Preço do produto", example = "15.99")
  private BigDecimal preco;

  @JsonProperty(required = true)
  @Schema(name = "categoria", description = "Categoria do produto")
  private CategoriaProduto categoria;

}
