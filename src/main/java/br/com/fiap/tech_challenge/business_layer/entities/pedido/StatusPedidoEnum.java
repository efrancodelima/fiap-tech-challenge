package br.com.fiap.tech_challenge.business_layer.entities.pedido;

import br.com.fiap.tech_challenge.business_layer.exceptions.BusinessRuleException;
import br.com.fiap.tech_challenge.business_layer.exceptions.messages.EnumExceptions;

public enum StatusPedidoEnum {

    AGUARDANDO_CHECKOUT, RECEBIDO, EM_PREPARACAO, PRONTO, FINALIZADO;

    public static StatusPedidoEnum fromString(String statusPedidoStr) throws BusinessRuleException {

        statusPedidoStr = statusPedidoStr == null ? null : statusPedidoStr.toUpperCase().trim();

        try {
            return StatusPedidoEnum.valueOf(statusPedidoStr);

        } catch (Exception e) {
            throw new BusinessRuleException(EnumExceptions.STATUS_PEDIDO.getMensagem());
        }
    }
}
