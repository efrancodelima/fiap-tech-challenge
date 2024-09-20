package br.com.fiap.tech_challenge.interface_layer.controllers.adapters.response_adapters;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.business_layer.entities.Pedido;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.pedido.StatusPedidoDto;

public final class StatusPedidoResponseAdapter {

    // Métodos públicos
    public static ResponseEntity<StatusPedidoDto> adaptar(Pedido pedido, HttpStatus httpStatus) {
        StatusPedidoDto response = adaptarParaStatusPedido(pedido);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<List<StatusPedidoDto>> adaptar(List<Pedido> pedidos,
            HttpStatus httpStatus) {
        List<StatusPedidoDto> response = adaptarParaListaPedidos(pedidos);
        return new ResponseEntity<>(response, httpStatus);
    }

    // Métodos privados
    private static StatusPedidoDto adaptarParaStatusPedido(Pedido pedido) {
        String status = pedido.getStatusPedido().getStatus().toString();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataHora = pedido.getStatusPedido().getDataHora().format(formatter);

        StatusPedidoDto response = new StatusPedidoDto();
        response.setNumeroPedido(pedido.getNumero());
        response.setStatus(status);
        response.setDataHora(dataHora);

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

}
