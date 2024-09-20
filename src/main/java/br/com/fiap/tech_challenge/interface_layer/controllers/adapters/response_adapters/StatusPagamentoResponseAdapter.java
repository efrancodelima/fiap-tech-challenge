package br.com.fiap.tech_challenge.interface_layer.controllers.adapters.response_adapters;

import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.business_layer.entities.pedido.Pedido;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.pedido.StatusPagamentoDto;

public final class StatusPagamentoResponseAdapter {

    public static ResponseEntity<StatusPagamentoDto> adaptar(Pedido pedido, HttpStatus httpStatus) {
        var status = pedido.getStatusPagamento().getStatus().toString();

        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataHora = pedido.getStatusPagamento().getDataHora().format(formatter);

        var response = new StatusPagamentoDto();
        response.setNumeroPedido(pedido.getNumero());
        response.setStatus(status);
        response.setDataHora(dataHora);

        return new ResponseEntity<>(response, httpStatus);
    }

}
