package br.com.fiap.tech_challenge.interface_layer.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemPedidoDto {

    @Schema(description = "Código do produto.", example = "1")
    public Long codigoProduto;

    @Schema(description = "Quantidade.", example = "1")
    public Integer quantidade;
}
