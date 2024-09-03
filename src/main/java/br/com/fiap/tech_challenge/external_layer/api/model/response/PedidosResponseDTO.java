package br.com.fiap.tech_challenge.external_layer.api.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PedidosResponseDTO {

  private List<PedidoResponseDTO> pedidos;

}
