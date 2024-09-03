package br.com.fiap.tech_challenge.interface_layer.gateways;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IPedidoGateway;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;
import br.com.fiap.tech_challenge.interface_layer.gateways.adapters.PedidoMapper;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.PedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.repositories.IPedidoRepository;

public class PedidoGateway implements IPedidoGateway {

    // Atributos
    @Autowired
    private IPedidoRepository pedidoJpaRepository;

    // Métodos públicos
    @Override
    public Pedido gravarPedido(Pedido pedido) throws Exception {
        PedidoJpa pedidoJpa = converterParaEntidadeJpa(pedido);
        pedidoJpa = pedidoJpaRepository.save(pedidoJpa);
        return converterParaEntidadeNegocio(pedidoJpa);
    }

    @Override
    public Pedido atualizarPedido(Pedido pedido) throws Exception {
        // O repositório JPA usa o método save() para insert e update
        return gravarPedido(pedido);
    }

    @Override
    public void removerPedido(Pedido pedido) throws Exception {
        PedidoJpa pedidoJpa = converterParaEntidadeJpa(pedido);
        pedidoJpaRepository.delete(pedidoJpa);
    }

    @Override
    public List<Pedido> listarPedidos() throws Exception, Exception {

        List<StatusPedidoEnum> statusListados = new ArrayList<>();
        statusListados.add(StatusPedidoEnum.RECEBIDO);
        statusListados.add(StatusPedidoEnum.EM_PREPARACAO);
        statusListados.add(StatusPedidoEnum.PRONTO);

        List<PedidoJpa> itensJpa = pedidoJpaRepository.listarPedidosPorStatusIn(statusListados);
        return converterParaEntidadesNegocio(itensJpa);
    }

    // Métodos privados
    private PedidoJpa converterParaEntidadeJpa(Pedido pedido) {
        return PedidoMapper.entidadeNegocioParaEntidadeJpa(pedido);
    }

    private Pedido converterParaEntidadeNegocio(PedidoJpa pedidoJpa) throws Exception {
        return PedidoMapper.entidadeJpaParaEntidadeNegocio(pedidoJpa);
    }

    private List<Pedido> converterParaEntidadesNegocio(List<PedidoJpa> pedidoJpa) throws Exception {
        return PedidoMapper.entidadesJpaParaEntidadesNegocio(pedidoJpa);
    }

}
