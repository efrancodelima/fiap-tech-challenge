package br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain_layer.exceptions.MyBusinessException;

public final class CategoriaProdutoRequestAdapter {

    public static CategoriaProduto adaptar(String categoriaStr) throws MyBusinessException {
        categoriaStr = categoriaStr == null ? null : categoriaStr.toUpperCase().trim();
        try {
            return CategoriaProduto.valueOf(categoriaStr);
        } catch (Exception e) {
            String msg = "A categoria é inválida.";
            throw new MyBusinessException(msg);
        }
    }

}
