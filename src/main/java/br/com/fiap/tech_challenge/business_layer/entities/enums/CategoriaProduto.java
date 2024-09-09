package br.com.fiap.tech_challenge.business_layer.entities.enums;

import br.com.fiap.tech_challenge.business_layer.exceptions.BusinessRuleException;
import br.com.fiap.tech_challenge.business_layer.exceptions.messages.CategoriaProdutoExceptions;

public enum CategoriaProduto {
    LANCHE,
    ACOMPANHAMENTO,
    BEBIDA,
    SOBREMESA;

    public static CategoriaProduto get(String categoriaStr) throws BusinessRuleException {

        categoriaStr = categoriaStr == null ? null : categoriaStr.toUpperCase().trim();

        try {
            return CategoriaProduto.valueOf(categoriaStr);

        } catch (Exception e) {
            throw new BusinessRuleException(CategoriaProdutoExceptions.CATEGORIA_INAVLIDA.getMensagem());
        }
    }
}
