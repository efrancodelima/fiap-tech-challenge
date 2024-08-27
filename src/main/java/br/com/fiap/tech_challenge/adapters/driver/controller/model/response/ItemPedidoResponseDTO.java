package br.com.fiap.tech_challenge.adapters.driver.controller.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ItemPedidoResponseDTO {

  @JsonProperty(required = true)
  @Schema(name = "idProduto", description = "Id do Produto", example = "1L")
  private Long idProduto;

  @Schema(name = "descricao", description = "Descrição do Produto", example = "Lanche Natural")
  private String descricao;

  @Schema(name = "valorUnitario", description = "Valor do Produto", example = "15.99")
  private BigDecimal valorUnitario;

  @JsonProperty(required = true)
  @Schema(name = "quantidade", description = "Quantidade", example = "2")
  private int quantidade;

  @Schema(name = "valorTotalProduto", description = "Valor total do produto", example = "30.98")
  private BigDecimal valorTotalPedido;

}
