package br.com.fiap.tech_challenge.application_layer.use_cases.pedido;

import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IPedidoGateway;
import br.com.fiap.tech_challenge.application_layer.services.Validar;
import br.com.fiap.tech_challenge.business_layer.entities.Pedido;

public final class BuscarPedido {

    public static Pedido buscar(IPedidoGateway gateway, Long numeroPedido) throws Exception {

        Validar.notNull(numeroPedido, EnumApplicationExceptions.PEDIDO_NUMERO_NULO);

        Pedido pedido = gateway.buscarPedido(numeroPedido);
        Validar.notNull(pedido, EnumNotFoundExceptions.PEDIDO_NAO_ENCONTRADO);

        return pedido;
    }

}
