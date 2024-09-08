package br.com.fiap.tech_challenge.interface_layer.dtos.Pedido;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NumeroPedidoDto {

    @Schema(description = "Número do pedido.", example = "123")
    public Long numeroPedido;
}
