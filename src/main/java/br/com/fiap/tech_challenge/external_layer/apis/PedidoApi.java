package br.com.fiap.tech_challenge.external_layer.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech_challenge.business_layer.entities.Pedido;
import br.com.fiap.tech_challenge.business_layer.entities.StatusPagamento;
import br.com.fiap.tech_challenge.external_layer.apis.interfaces.IPedidoApi;
import br.com.fiap.tech_challenge.interface_layer.controllers.PedidoController;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.PagamentoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.Pedido.PedidoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.Pedido.StatusPagamentoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.Pedido.StatusPedidoDto;

@RestController
@RequestMapping("/pedidos")
public class PedidoApi implements IPedidoApi {

    @Autowired
    private PedidoController controller;

    @Override
    public ResponseEntity<StatusPedidoDto> fazerCheckout(PedidoDto pedidoDto) throws Exception {
        return controller.fazerCheckout(pedidoDto);
    }

    @Override
    public ResponseEntity<StatusPedidoDto> atualizarStatusPedido(long numeroPedido) throws Exception {
        return controller.atualizarStatusPedido(numeroPedido);
    }

    @Override
    public ResponseEntity<StatusPagamentoDto> consultarStatusPagamento(long numeroPedido) throws Exception {
        return controller.consultarStatusPagamento(numeroPedido);
    }

    @Override
    public ResponseEntity<List<Pedido>> listarPedidos() throws Exception {
        return controller.listarPedidos();
    }

    @Override
    public ResponseEntity<Void> webhookMercadoPago(PagamentoDto pagamentoDto) throws Exception {
        return controller.webhookMercadoPago(pagamentoDto);
    }

}
