package br.com.fiap.tech_challenge.interface_layer.controllers.dtos.pedido;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDto {

    @Schema(description = "Código do produto.", example = "1")
    public Long codigoProduto;

    @Schema(description = "Quantidade.", example = "1")
    public Integer quantidade;
}
