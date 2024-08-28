package br.com.fiap.tech_challenge.domain_layer.interfaces;

import br.com.fiap.tech_challenge.domain_layer.business_entities.ItemPedido;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;

public interface IPedido {
    
    public void adicionarItem(ItemPedido item) throws BusinessRulesExceptions;

    public void alterarItem(int numeroItem, ItemPedido item) throws BusinessRulesExceptions;

    public void removerItem(int numeroItem) throws BusinessRulesExceptions;

    public void fazerCheckout() throws BusinessRulesExceptions;

    public void atualizarStatusPedido() throws BusinessRulesExceptions;
}
