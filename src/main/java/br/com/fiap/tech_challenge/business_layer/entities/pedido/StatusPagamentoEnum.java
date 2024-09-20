package br.com.fiap.tech_challenge.business_layer.entities.pedido;

import br.com.fiap.tech_challenge.business_layer.exceptions.BusinessRuleException;
import br.com.fiap.tech_challenge.business_layer.exceptions.messages.EnumExceptions;

public enum StatusPagamentoEnum {

    AGUARDANDO_PAGAMENTO, APROVADO, REPROVADO;

    public static StatusPagamentoEnum fromString(String statusPagamentoStr) throws BusinessRuleException {

        statusPagamentoStr = statusPagamentoStr == null ? null : statusPagamentoStr.toUpperCase().trim();

        try {
            return StatusPagamentoEnum.valueOf(statusPagamentoStr);

        } catch (Exception e) {
            throw new BusinessRuleException(EnumExceptions.STATUS_PAGAMENTO.getMensagem());
        }
    }
}
