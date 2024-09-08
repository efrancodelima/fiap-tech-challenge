package br.com.fiap.tech_challenge.interface_layer.controllers.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.interface_layer.dtos.PedidoDto;
import br.com.fiap.tech_challenge.interface_layer.dtos.Pedido.StatusDto;

public interface IPedidoController {

    ResponseEntity<StatusDto> fazerCheckout(PedidoDto pedidoDto) throws Exception;

    ResponseEntity<StatusDto> atualizarStatusPedido(Long numeroPedido) throws Exception;

    ResponseEntity<StatusDto> consultarStatusPagamento(Long numeroPedido) throws Exception;

    ResponseEntity<List<StatusDto>> listarPedidos() throws Exception;

}
