package br.com.fiap.tech_challenge.external_layer.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedidoDTO {

    @JsonProperty(required = true)
    @Schema(name = "idProduto", description = "Id do Produto", example = "1L")
    private Long idProduto;

    @JsonProperty(required = true)
    @Schema(name = "quantidade", description = "Quantidade", example = "2")
    private int quantidade;

}
