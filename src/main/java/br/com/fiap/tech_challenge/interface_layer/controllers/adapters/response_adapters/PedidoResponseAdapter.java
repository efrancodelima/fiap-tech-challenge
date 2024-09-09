package br.com.fiap.tech_challenge.interface_layer.controllers.adapters.response_adapters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPagamento;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.Pedido.StatusDto;

public final class PedidoResponseAdapter {

    // Métodos públicos
    public static ResponseEntity<StatusDto> adaptarParaStatusPedido(Pedido pedido, HttpStatus httpStatus) {

        StatusDto response = adaptarParaStatusPedido(pedido);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<StatusDto> adaptarParaStatusPagamento(long numeroPedido,
            StatusPagamento statusPagamento, HttpStatus httpStatus) {

        StatusDto response = adaptarParaStatusPagamento(numeroPedido, statusPagamento);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<List<StatusDto>> adaptarParaListaPedidos(List<Pedido> pedidos, HttpStatus httpStatus) {

        List<StatusDto> response = adaptarParaListaPedidos(pedidos);
        return new ResponseEntity<>(response, httpStatus);
    }

    // Métodos privados
    private static StatusDto adaptarParaStatusPedido(Pedido pedido) {

        StatusDto response = new StatusDto();
        response.setNumeroPedido(pedido.getNumero());
        response.setStatusPedido(pedido.getStatusPedido().getStatus().toString());
        response.setDataHora(adaptarLocalDateTime(pedido.getStatusPedido().getDataHora()));

        return response;
    }

    public static StatusDto adaptarParaStatusPagamento(long numeroPedido, StatusPagamento statusPagamento) {

        var response = new StatusDto();
        response.setNumeroPedido(numeroPedido);
        response.setStatusPedido(statusPagamento.getStatus().toString());
        response.setDataHora(adaptarLocalDateTime(statusPagamento.getDataHora()));

        return response;
    }

    private static List<StatusDto> adaptarParaListaPedidos(List<Pedido> pedidos) {

        List<StatusDto> response = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            StatusDto status = adaptarParaStatusPedido(pedido);
            response.add(status);
        }

        return response;
    }

    private static String adaptarLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

}
