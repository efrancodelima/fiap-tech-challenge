package br.com.fiap.tech_challenge.application_layer.use_cases.produto;

import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.application_layer.services.Validar;
import br.com.fiap.tech_challenge.business_layer.entities.produto.Produto;

public final class CadastrarProduto {

    public static Produto cadastrar(IProdutoGateway gateway, Produto produto) throws Exception {
        Validar.notNull(produto, EnumApplicationExceptions.PRODUTO_NULO);
        return gateway.gravarProduto(produto);
    }

}
