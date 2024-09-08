package br.com.fiap.tech_challenge.external_layer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.external_layer.api.interfaces.IPedidoApi;
import br.com.fiap.tech_challenge.interface_layer.controllers.PedidoController;
import br.com.fiap.tech_challenge.interface_layer.dtos.PedidoDto;

@RestController
@RequestMapping("/pedidos")
public class PedidoApi implements IPedidoApi {

    @Autowired
    private PedidoController controller;

    @Override
    public ResponseEntity<Pedido> fazerCheckout(PedidoDto pedidoDto) throws Exception {
        return controller.fazerCheckout(pedidoDto);
    }

}
