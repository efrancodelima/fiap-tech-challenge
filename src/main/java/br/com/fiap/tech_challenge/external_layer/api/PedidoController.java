package br.com.fiap.tech_challenge.external_layer.api;

import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import br.com.fiap.tech_challenge.core.domain.ports.in.PedidoServicePort;
import br.com.fiap.tech_challenge.interface_layer.controller.mapper.PedidoDTOMapper;
import br.com.fiap.tech_challenge.interface_layer.controller.model.request.CadastrarPedidoDTO;
import br.com.fiap.tech_challenge.interface_layer.controller.model.response.PedidosResponseDTO;
import br.com.fiap.tech_challenge.interface_layer.controller.swagger.PedidoSwaggerInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/pedido")
public class PedidoController implements PedidoSwaggerInterface {

    private final PedidoServicePort service;
    private final PedidoDTOMapper pedidoDTOMapper;

    @Autowired
    public PedidoController(PedidoServicePort service, PedidoDTOMapper pedidoDTOMapper) {
        this.service = service;
        this.pedidoDTOMapper = pedidoDTOMapper;
    }

    @Override
    public ResponseEntity<String> cadastrarPedido(CadastrarPedidoDTO cadastrar) {
        Pedido pedido = pedidoDTOMapper.cadastrarToPedido(cadastrar);
        service.cadastrarPedido(pedido);
        return new ResponseEntity<>("Pedido cadastrado com sucesso",
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> listarPedidos() {
        PedidosResponseDTO pedidoDTOS = new PedidosResponseDTO();
        List<Pedido> pedidos = service.listarPedidos();
        pedidoDTOS.setPedidos(pedidoDTOMapper.pedidosToPedidosResponseDTO(pedidos));
        return new ResponseEntity<>(pedidoDTOS, HttpStatus.OK);
    }

}
