package br.com.fiap.tech_challenge.external_layer.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech_challenge.external_layer.api.interfaces.IPedidoApi;
import br.com.fiap.tech_challenge.interface_layer.controllers.PedidoController;
import br.com.fiap.tech_challenge.interface_layer.dtos.PedidoDto;
import br.com.fiap.tech_challenge.interface_layer.dtos.Pedido.StatusDto;

@RestController
@RequestMapping("/pedidos")
public class PedidoApi implements IPedidoApi {

    @Autowired
    private PedidoController controller;

    @Override
    public ResponseEntity<StatusDto> fazerCheckout(PedidoDto pedidoDto) throws Exception {
        return controller.fazerCheckout(pedidoDto);
    }

    @Override
    public ResponseEntity<StatusDto> atualizarStatusPedido(Long numeroPedido) throws Exception {
        return controller.atualizarStatusPedido(numeroPedido);
    }

    @Override
    public ResponseEntity<StatusDto> consultarStatusPagamento(Long numeroPedido) throws Exception {
        return controller.consultarStatusPagamento(numeroPedido);
    }

    @Override
    public ResponseEntity<List<StatusDto>> listarPedidos() throws Exception {
        return controller.listarPedidos();
    }

}
