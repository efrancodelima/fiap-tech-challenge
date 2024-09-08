package br.com.fiap.tech_challenge.interface_layer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.use_cases.ClienteUseCase;
import br.com.fiap.tech_challenge.application_layer.use_cases.PedidoUseCase;
import br.com.fiap.tech_challenge.application_layer.use_cases.ProdutoUseCase;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.domain_layer.business_entities.ItemPedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.exceptions.MyBusinessException;
import br.com.fiap.tech_challenge.interface_layer.controllers.interfaces.IPedidoController;
import br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters.CpfRequestAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters.ItemPedidoRequestAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters.PedidoResponseAdapter;
import br.com.fiap.tech_challenge.interface_layer.dtos.ItemPedidoDto;
import br.com.fiap.tech_challenge.interface_layer.dtos.PedidoDto;
import br.com.fiap.tech_challenge.interface_layer.gateways.ClienteGateway;
import br.com.fiap.tech_challenge.interface_layer.gateways.PedidoGateway;
import br.com.fiap.tech_challenge.interface_layer.gateways.ProdutoGateway;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.MyNotFoundException;
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
    public ResponseEntity<Pedido> fazerCheckout(PedidoDto pedidoDto) throws Exception {

        Cliente cliente = null;
        Long cpfLong = pedidoDto.getCpfCliente();
        if (cpfLong != null && cpfLong != 0) {
            Cpf cpf = CpfRequestAdapter.adaptar(cpfLong);
            cliente = clienteUseCase.buscarClientePorCpf(cpf);
        }

        List<ItemPedido> itens;
        List<ItemPedidoDto> itensDto = pedidoDto.getItens();
        itens = ItemPedidoRequestAdapter.adaptar(produtoUseCase, itensDto);

        Pedido pedido = new Pedido(cliente, itens);
        pedido = pedidoUseCase.fazerCheckout(pedido);
        return PedidoResponseAdapter.adaptar(pedido);
    }
}
