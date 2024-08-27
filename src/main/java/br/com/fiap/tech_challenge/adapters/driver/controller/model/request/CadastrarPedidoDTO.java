package br.com.fiap.tech_challenge.adapters.driver.controller.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CadastrarPedidoDTO {

    @Schema(name = "itens", description = "Lista de itens do pedido")
    private List<ItemPedidoDTO> itens = new ArrayList<>();

}
