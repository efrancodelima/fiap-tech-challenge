package br.com.fiap.tech_challenge.application_layer.use_cases.pedido;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IPedidoGateway;
import br.com.fiap.tech_challenge.application_layer.services.Validar;
import br.com.fiap.tech_challenge.business_layer.entities.pedido.Pedido;
import br.com.fiap.tech_challenge.business_layer.entities.pedido.StatusPagamento;
import br.com.fiap.tech_challenge.business_layer.entities.pedido.StatusPagamentoEnum;

public final class FazerCheckoutPedido {

    public static Pedido fazerCheckout(IPedidoGateway gateway, Pedido pedido) throws Exception {

        Validar.notNull(pedido, EnumApplicationExceptions.PEDIDO_NULO);

        pedido.fazerCheckout();
        pedido = gateway.gravarPedido(pedido);

        // Por ora, o código do pagamento é igual ao do pedido
        // Após a integração com o Mercado Pago isso vai mudar
        long codigo = pedido.getNumero();
        var status = StatusPagamentoEnum.AGUARDANDO_PAGAMENTO;
        var dataHora = LocalDateTime.now();
        var mock = new StatusPagamento(codigo, status, dataHora);
        pedido.setStatusPagamento(mock);
        gateway.gravarPedido(pedido);

        return pedido;
    }

}
