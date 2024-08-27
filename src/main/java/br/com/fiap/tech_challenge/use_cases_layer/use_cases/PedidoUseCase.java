package br.com.fiap.tech_challenge.use_cases_layer.use_cases;

import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.fiap.tech_challenge.core.domain.model.enums.StatusPedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.use_cases_layer.repository.PedidoRepository;

public class PedidoUseCase {

    public static void checkout(PedidoRepository repository, Pedido pedido) throws BusinessRulesExceptions {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        pedido.setDataHoraCheckout(dataHoraAtual);
        pedido.setSituacao(StatusPedido.RECEBIDO);
        pedido.setDataHoraSituacao(dataHoraAtual);
        repository.alterarPedido(pedido);
    }

    public static ArrayList<Pedido> listarPedidos(PedidoRepository repository) {
        return repository.listarPedidosAbertos();
    }
}
