package br.com.fiap.tech_challenge.interface_layer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.use_cases.ClienteUseCase;
import br.com.fiap.tech_challenge.application_layer.use_cases.PedidoUseCase;
import br.com.fiap.tech_challenge.application_layer.use_cases.ProdutoUseCase;
import br.com.fiap.tech_challenge.business_layer.entities.Cliente;
import br.com.fiap.tech_challenge.business_layer.entities.Cpf;
import br.com.fiap.tech_challenge.business_layer.entities.ItemPedido;
import br.com.fiap.tech_challenge.business_layer.entities.Pedido;
import br.com.fiap.tech_challenge.business_layer.entities.StatusPagamento;
import br.com.fiap.tech_challenge.interface_layer.controllers.adapters.request_adapters.ItemPedidoRequestAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.adapters.response_adapters.PedidoResponseAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.ItemPedidoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.Pedido.PedidoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.Pedido.StatusDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.interfaces.IPedidoController;
import br.com.fiap.tech_challenge.interface_layer.gateways.ClienteGateway;
import br.com.fiap.tech_challenge.interface_layer.gateways.PedidoGateway;
import br.com.fiap.tech_challenge.interface_layer.gateways.ProdutoGateway;
import jakarta.annotation.PostConstruct;

@Component
public class PedidoController implements IPedidoController {

    // Atributos
    @Autowired
    PedidoGateway pedidoGateway;
    PedidoUseCase pedidoUseCase;

    @Autowired
    ClienteGateway clienteGateway;
    ClienteUseCase clienteUseCase;

    @Autowired
    ProdutoGateway produtoGateway;
    ProdutoUseCase produtoUseCase;

    // Método de inicialização
    // A partir de um atributo injetado, inicializa um atributo não injetado
    @PostConstruct
    private void init() {
        this.pedidoUseCase = new PedidoUseCase(pedidoGateway);
        this.clienteUseCase = new ClienteUseCase(clienteGateway);
        this.produtoUseCase = new ProdutoUseCase(produtoGateway);
    }

    @Override
    public ResponseEntity<StatusDto> fazerCheckout(PedidoDto pedidoDto) throws Exception {

        Cliente cliente = null;
        Long cpfLong = pedidoDto.getCpfCliente();
        if (cpfLong != null) {
            Cpf cpf = new Cpf(cpfLong);
            cliente = clienteUseCase.buscarClientePorCpf(cpf);
        }

        List<ItemPedido> itens;
        List<ItemPedidoDto> itensDto = pedidoDto.getItens();
        itens = ItemPedidoRequestAdapter.adaptar(produtoUseCase, itensDto);

        Pedido pedido = new Pedido(cliente, itens);
        pedido = pedidoUseCase.fazerCheckout(pedido);
        return PedidoResponseAdapter.adaptarParaStatusPedido(pedido, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<StatusDto> atualizarStatusPedido(Long numeroPedido) throws Exception {
        Pedido pedido = pedidoUseCase.atualizarStatusPedido(numeroPedido);
        return PedidoResponseAdapter.adaptarParaStatusPedido(pedido, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StatusDto> consultarStatusPagamento(Long numeroPedido) throws Exception {
        StatusPagamento statusPagamento = pedidoUseCase.consultarStatusPagamento(numeroPedido);
        return PedidoResponseAdapter.adaptarParaStatusPagamento(numeroPedido, statusPagamento, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<StatusDto>> listarPedidos() throws Exception {

        List<Pedido> pedidos = pedidoUseCase.listarPedidos();

        if (pedidos.size() > 0) {
            return PedidoResponseAdapter.adaptarParaListaPedidos(pedidos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
