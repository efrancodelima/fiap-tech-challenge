package br.com.fiap.tech_challenge.interface_layer.controllers.dtos.pedido;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

    @Schema(description = "CPF do cliente (opcional).", example = "11122233396")
    public Long cpfCliente;

    @Schema(description = "Itens do pedido.", example = "[{\"codigoProduto\": 1, \"quantidade\": 2}, {\"codigoProduto\": 2, \"quantidade\": 1}]")
    public List<ItemPedidoDto> itens;

}
