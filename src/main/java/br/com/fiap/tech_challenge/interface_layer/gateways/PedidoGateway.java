package br.com.fiap.tech_challenge.interface_layer.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IPedidoGateway;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.PedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.mappers.PedidoMapper;
import br.com.fiap.tech_challenge.interface_layer.gateways.repositories.IPedidoRepository;

@Component
public class PedidoGateway implements IPedidoGateway {

    // Atributos
    @Autowired
    private IPedidoRepository pedidoJpaRepository;

    // Métodos públicos
    @Override
    public Pedido gravarPedido(Pedido pedido) throws Exception {

        PedidoJpa pedidoJpa = PedidoMapper.getPedidoJpa(pedido);
        pedidoJpa = pedidoJpaRepository.save(pedidoJpa);
        return PedidoMapper.getPedido(pedidoJpa);
    }

    @Override
    public void atualizarPedido(Pedido pedido) throws Exception {

        PedidoJpa pedidoJpa = PedidoMapper.getPedidoJpa(pedido);
        pedidoJpaRepository.save(pedidoJpa);
    }

    @Override
    public Pedido buscarPedido(long numeroPedido) throws Exception {

        Optional<PedidoJpa> optionalPedido = pedidoJpaRepository.findById(numeroPedido);
        return optionalPedido.isPresent() ? PedidoMapper.getPedido(optionalPedido.get()) : null;
    }

    @Override
    public List<Pedido> buscarTodosOsPedidos() throws Exception, Exception {

        List<PedidoJpa> pedidosJpa = pedidoJpaRepository.findAll();
        return PedidoMapper.getListPedido(pedidosJpa);
    }

}
