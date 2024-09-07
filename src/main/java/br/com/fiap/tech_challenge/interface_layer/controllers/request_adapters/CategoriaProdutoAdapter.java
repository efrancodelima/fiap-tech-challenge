package br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;

public class CategoriaProdutoAdapter {

    public static CategoriaProduto adaptar(String categoriaStr) throws Exception {
        categoriaStr = categoriaStr == null ? null : categoriaStr.toUpperCase().trim();
        try {
            return CategoriaProduto.valueOf(categoriaStr);
        } catch (Exception e) {
            String msg = "A categoria é inválida.";
            throw new Exception(msg);
        }
    }

}
