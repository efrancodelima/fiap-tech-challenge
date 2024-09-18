package br.com.fiap.tech_challenge.interface_layer.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.use_cases.cliente.BuscarClientePeloCpf;
import br.com.fiap.tech_challenge.application_layer.use_cases.pedido.AtualizarStatusPagamento;
import br.com.fiap.tech_challenge.application_layer.use_cases.pedido.AtualizarStatusPedido;
import br.com.fiap.tech_challenge.application_layer.use_cases.pedido.BuscarPedido;
import br.com.fiap.tech_challenge.application_layer.use_cases.pedido.FazerCheckoutPedido;
import br.com.fiap.tech_challenge.application_layer.use_cases.pedido.ListarPedidos;
import br.com.fiap.tech_challenge.business_layer.entities.Cliente;
import br.com.fiap.tech_challenge.business_layer.entities.Cpf;
import br.com.fiap.tech_challenge.business_layer.entities.ItemPedido;
import br.com.fiap.tech_challenge.business_layer.entities.Pedido;
import br.com.fiap.tech_challenge.business_layer.entities.Produto;
import br.com.fiap.tech_challenge.interface_layer.controllers.adapters.request_adapters.ItemPedidoRequestAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.adapters.request_adapters.PagamentoRequestAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.adapters.response_adapters.StatusPedidoResponseAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.adapters.response_adapters.StatusPagamentoResponseAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.ItemPedidoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.PagamentoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.Pedido.PedidoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.Pedido.StatusPagamentoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.Pedido.StatusPedidoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.interfaces.IPedidoController;
import br.com.fiap.tech_challenge.interface_layer.gateways.ClienteGateway;
import br.com.fiap.tech_challenge.interface_layer.gateways.PedidoGateway;
import br.com.fiap.tech_challenge.interface_layer.gateways.ProdutoGateway;

@Component
public class PedidoController implements IPedidoController {

    // Atributos
    @Autowired
    PedidoGateway pedidoGateway;

    @Autowired
    ClienteGateway clienteGateway;

    @Autowired
    ProdutoGateway produtoGateway;

    // Métodos públicos
    @Override
    public ResponseEntity<StatusPedidoDto> fazerCheckout(PedidoDto pedidoDto) throws Exception {
        Cliente cliente = getClientePedidoDto(pedidoDto);
        List<ItemPedido> itens = getItensPedidoDto(pedidoDto);

        Pedido pedido = new Pedido(cliente, itens);
        pedido = FazerCheckoutPedido.fazerCheckout(pedidoGateway, pedido);

        return StatusPedidoResponseAdapter.adaptar(pedido, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<StatusPedidoDto> atualizarStatusPedido(Long numeroPedido) throws Exception {
        Pedido pedido = AtualizarStatusPedido.atualizar(pedidoGateway, numeroPedido);
        return StatusPedidoResponseAdapter.adaptar(pedido, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StatusPagamentoDto> consultarStatusPagamento(Long numeroPedido) throws Exception {
        Pedido pedido = BuscarPedido.buscar(pedidoGateway, numeroPedido);
        return StatusPagamentoResponseAdapter.adaptar(pedido, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<StatusPedidoDto>> listarPedidos() throws Exception {
        List<Pedido> pedidos = ListarPedidos.listar(pedidoGateway);

        if (pedidos.size() > 0) {
            return StatusPedidoResponseAdapter.adaptar(pedidos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<Void> webhookMercadoPago(PagamentoDto pagamentoDto) throws Exception {
        var statusPagamento = PagamentoRequestAdapter.adaptar(pagamentoDto);
        AtualizarStatusPagamento.atualizar(pedidoGateway, statusPagamento);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // Métodos privados
    private Cliente getClientePedidoDto(PedidoDto pedidoDto) throws Exception {
        Cliente cliente = null;
        Long cpfLong = pedidoDto.getCpfCliente();

        if (cpfLong != null) {
            Cpf cpf = new Cpf(cpfLong);
            cliente = BuscarClientePeloCpf.buscar(clienteGateway, cpf);
        }
        return cliente;
    }

    private List<ItemPedido> getItensPedidoDto(PedidoDto pedidoDto) throws Exception {
        List<Produto> produtos = new ArrayList<>();
        List<ItemPedidoDto> itensDto = pedidoDto.getItens();

        for (ItemPedidoDto item : itensDto) {
            var produto = produtoGateway.buscarProduto(item.codigoProduto);
            produtos.add(produto);
        }
        return ItemPedidoRequestAdapter.adaptar(itensDto, produtos);
    }

}
