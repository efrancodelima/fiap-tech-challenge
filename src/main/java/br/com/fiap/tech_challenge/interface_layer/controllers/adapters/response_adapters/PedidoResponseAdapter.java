package br.com.fiap.tech_challenge.interface_layer.controllers.adapters.response_adapters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.business_layer.entities.Pedido;
import br.com.fiap.tech_challenge.business_layer.entities.StatusPagamento;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.Pedido.StatusPedidoDto;

public final class PedidoResponseAdapter {

    // Métodos públicos
    public static ResponseEntity<StatusPedidoDto> adaptar(Pedido pedido, HttpStatus httpStatus) {

        StatusPedidoDto response = adaptarParaStatusPedido(pedido);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<StatusPedidoDto> adaptar(long numeroPedido,
            StatusPagamento statusPagamento, HttpStatus httpStatus) {

        StatusPedidoDto response = adaptarParaStatusPagamento(numeroPedido, statusPagamento);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<List<StatusPedidoDto>> adaptar(List<Pedido> pedidos,
            HttpStatus httpStatus) {

        List<StatusPedidoDto> response = adaptarParaListaPedidos(pedidos);
        return new ResponseEntity<>(response, httpStatus);
    }

    // Métodos privados
    private static StatusPedidoDto adaptarParaStatusPedido(Pedido pedido) {

        StatusPedidoDto response = new StatusPedidoDto();
        response.setNumeroPedido(pedido.getNumero());
        response.setStatusPedido(pedido.getStatusPedido().getStatus().toString());
        response.setDataHora(adaptarLocalDateTime(pedido.getStatusPedido().getDataHora()));

        return response;
    }

    public static StatusPedidoDto adaptarParaStatusPagamento(long numeroPedido, StatusPagamento statusPagamento) {

        var response = new StatusPedidoDto();
        response.setNumeroPedido(numeroPedido);
        response.setStatusPedido(statusPagamento.getStatus().toString());
        response.setDataHora(adaptarLocalDateTime(statusPagamento.getDataHora()));

        return response;
    }

    private static List<StatusPedidoDto> adaptarParaListaPedidos(List<Pedido> pedidos) {

        List<StatusPedidoDto> response = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            StatusPedidoDto status = adaptarParaStatusPedido(pedido);
            response.add(status);
        }

        return response;
    }

    private static String adaptarLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

}
