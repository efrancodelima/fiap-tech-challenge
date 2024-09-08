package br.com.fiap.tech_challenge.interface_layer.controllers.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.interface_layer.dtos.PedidoDto;
import br.com.fiap.tech_challenge.interface_layer.dtos.Pedido.StatusPedidoDto;

public interface IPedidoController {

    ResponseEntity<StatusPedidoDto> fazerCheckout(PedidoDto pedidoDto) throws Exception;

    ResponseEntity<StatusPedidoDto> atualizarStatusPedido(Long numeroPedido) throws Exception;

}
