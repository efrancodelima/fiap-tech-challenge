package br.com.fiap.tech_challenge.interface_layer.gateways;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IPedidoGateway;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.PedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.MyNotFoundException;
import br.com.fiap.tech_challenge.interface_layer.gateways.mappers.PedidoMapper;
import br.com.fiap.tech_challenge.interface_layer.gateways.repositories.IPedidoRepository;

@Component
public class PedidoGateway implements IPedidoGateway {

    // Atributos
    @Autowired
    private IPedidoRepository pedidoJpaRepository;
    private final String PEDIDO_NAO_ENCONTRADO = "Não foi encontrado nenhum pedido para o código informado.";

    // Métodos públicos
    @Override
    public Pedido gravarPedido(Pedido pedido) throws Exception {

        PedidoJpa pedidoJpa = PedidoMapper.mapearParaEntidadeJpa(pedido);
        pedidoJpa = pedidoJpaRepository.save(pedidoJpa);
        return PedidoMapper.mapearParaEntidadeNegocio(pedidoJpa);
    }

    @Override
    public void atualizarPedido(Pedido pedido) throws Exception {

        if (!pedidoJpaRepository.existsById(pedido.getNumero())) {
            throw new MyNotFoundException(PEDIDO_NAO_ENCONTRADO);
        }

        PedidoJpa pedidoJpa = PedidoMapper.mapearParaEntidadeJpa(pedido);
        pedidoJpaRepository.save(pedidoJpa);
    }

    @Override
    public void removerPedido(Pedido pedido) throws Exception {

        if (!pedidoJpaRepository.existsById(pedido.getNumero())) {
            throw new MyNotFoundException(PEDIDO_NAO_ENCONTRADO);
        }

        PedidoJpa pedidoJpa = PedidoMapper.mapearParaEntidadeJpa(pedido);
        pedidoJpaRepository.delete(pedidoJpa);
    }

    @Override
    public Pedido buscarPedido(long numeroPedido) throws Exception {

        PedidoJpa pedidoJpa;
        Optional<PedidoJpa> optionalPedido = pedidoJpaRepository.findById(numeroPedido);

        if (optionalPedido.isPresent()) {
            pedidoJpa = optionalPedido.get();
        } else {
            throw new MyNotFoundException(PEDIDO_NAO_ENCONTRADO);
        }

        return PedidoMapper.mapearParaEntidadeNegocio(pedidoJpa);
    }

    @Override
    public List<Pedido> listarPedidos() throws Exception, Exception {

        List<StatusPedidoEnum> statusListados = new ArrayList<>();
        statusListados.add(StatusPedidoEnum.RECEBIDO);
        statusListados.add(StatusPedidoEnum.EM_PREPARACAO);
        statusListados.add(StatusPedidoEnum.PRONTO);

        List<PedidoJpa> pedidosJpa = pedidoJpaRepository.listarPedidosPorStatusIn(statusListados);
        return PedidoMapper.mapearParaEntidadesNegocio(pedidosJpa);

    }

}
