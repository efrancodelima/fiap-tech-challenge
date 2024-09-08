package br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPagamento;
import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPedido;
import br.com.fiap.tech_challenge.interface_layer.dtos.Pedido.StatusDto;

public final class PedidoResponseAdapter {

    // Métodos públicos
    public static ResponseEntity<StatusDto> adaptarParaStatusPedido(Pedido pedido, HttpStatus httpStatus) {

        var response = new StatusDto();
        long numeroPedido = pedido.getNumero();
        String status = pedido.getStatusPedido().getStatus().toString();
        String dataHora = adaptarLocalDateTime(pedido.getStatusPedido().getDataHora());

        response.setNumeroPedido(numeroPedido);
        response.setStatusPedido(status);
        response.setDataHora(dataHora);

        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<StatusDto> adaptarParaStatusPagamento(long numeroPedido,
            StatusPagamento statusPagamento, HttpStatus httpStatus) {

        var response = new StatusDto();
        String status = statusPagamento.getStatus().toString();
        String dataHora = adaptarLocalDateTime(statusPagamento.getDataHora());

        response.setNumeroPedido(numeroPedido);
        response.setStatusPedido(status);
        response.setDataHora(dataHora);

        return new ResponseEntity<>(response, httpStatus);
    }

    // Métodos privados
    private static String adaptarLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

}
