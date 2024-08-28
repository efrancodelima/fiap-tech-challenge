package br.com.fiap.tech_challenge.application_layer.interfaces.use_cases;

import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;

public interface IPedidoUseCase {

    void atualizarStatus(Pedido pedido) throws BusinessRulesExceptions;

    // Para usar com o webhook
    void confirmarPagamento(Pedido pedido, boolean pagamentoProcessado);
    
    boolean consultarStatusPagamento(Pedido pedido);

    void fazerCheckout(Pedido pedido) throws BusinessRulesExceptions;

    List<Pedido> listarPedidos();

    

}
