package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.ResourceNotFoundException;

public interface IPedidoGateway {

    public Pedido gravarPedido(Pedido pedido) throws Exception;

    public void atualizarPedido(Pedido pedido) throws ResourceNotFoundException, Exception;

    public void removerPedido(Pedido pedido) throws ResourceNotFoundException, Exception;

    public List<Pedido> listarPedidos() throws ResourceNotFoundException, Exception;

}
