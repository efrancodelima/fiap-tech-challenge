package br.com.fiap.tech_challenge.application_layer.interfaces.use_cases;

import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;

public interface IPedidoUseCase {

    void atualizarStatus(Pedido pedido) throws BusinessRulesExceptions, Exception;

    // Para usar com o webhook
    void confirmarPagamento(Pedido pedido, boolean pagamentoProcessado) throws BusinessRulesExceptions, Exception;

    boolean consultarStatusPagamento(Pedido pedido) throws BusinessRulesExceptions, Exception;

    void fazerCheckout(Pedido pedido) throws BusinessRulesExceptions, Exception;

    List<Pedido> listarPedidos() throws BusinessRulesExceptions, Exception;

}
