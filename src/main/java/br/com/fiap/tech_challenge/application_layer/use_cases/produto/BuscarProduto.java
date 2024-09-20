package br.com.fiap.tech_challenge.application_layer.use_cases.produto;

import br.com.fiap.tech_challenge.application_layer.exceptions.ResourceNotFoundException;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.application_layer.services.Validar;
import br.com.fiap.tech_challenge.business_layer.entities.produto.Produto;

public final class BuscarProduto {

    // Métodos públicos
    public static Produto buscar(IProdutoGateway gateway, Long codigoProduto) throws Exception {
        Validar.notNull(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_NULO);
        Validar.maiorQueZero(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_MIN);

        Produto produto = gateway.buscarProduto(codigoProduto);
        Validar.notNull(produto, EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO);

        return produto;
    }

}
