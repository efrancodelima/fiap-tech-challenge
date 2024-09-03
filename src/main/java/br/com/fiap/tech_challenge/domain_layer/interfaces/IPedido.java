package br.com.fiap.tech_challenge.domain_layer.interfaces;

import br.com.fiap.tech_challenge.domain_layer.business_entities.ItemPedido;

public interface IPedido {
    
    public void adicionarItem(ItemPedido item) throws Exception;

    public void alterarItem(int numeroItem, ItemPedido item) throws Exception;

    public void removerItem(int numeroItem) throws Exception;

    public void fazerCheckout() throws Exception;

    public void atualizarStatusPedido() throws Exception;
}
