package br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.interface_layer.dtos.Pedido.StatusPedidoDto;

public final class PedidoResponseAdapter {

    // public static ResponseEntity<Pedido> adaptar(Pedido pedido) {
    // return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    // }

    public static ResponseEntity<StatusPedidoDto> adaptarParaStatusPedido(Pedido pedido, HttpStatus httpStatus) {

        var response = new StatusPedidoDto();
        response.setNumeroPedido(pedido.getNumero());
        response.setStatusPedido(pedido.getStatusPedido().getStatus().toString());
        response.setDataHoraStatus(pedido.getStatusPedido().getDataHora());

        return new ResponseEntity<>(response, httpStatus);
    }

}