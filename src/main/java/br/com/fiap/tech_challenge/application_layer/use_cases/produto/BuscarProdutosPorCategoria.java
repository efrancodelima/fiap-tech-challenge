package br.com.fiap.tech_challenge.application_layer.use_cases.produto;

import java.util.List;

import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.application_layer.services.Validar;
import br.com.fiap.tech_challenge.business_layer.entities.produto.CategoriaProduto;
import br.com.fiap.tech_challenge.business_layer.entities.produto.Produto;

public final class BuscarProdutosPorCategoria {

    public static List<Produto> buscar(IProdutoGateway gateway, CategoriaProduto categoria)
            throws Exception {

        Validar.notNull(categoria, EnumApplicationExceptions.CATEGORIA_NULA);

        List<Produto> produtos = gateway.buscarProdutosPorCategoria(categoria);
        Validar.listNotEmpty(produtos, EnumNotFoundExceptions.PRODUTO_LISTA_VAZIA);

        return produtos;
    }

}
