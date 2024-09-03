package br.com.fiap.tech_challenge.interface_layer.gateways.jpa_mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.ProdutoJpa;

public final class ProdutoMapper {

    // Métodos públicos
    public static ProdutoJpa entidadeNegocioParaEntidadeJpa(Produto produto) {
        return new ProdutoJpa(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(),
                produto.getCategoria());
    }

    public static Produto entidadeJpaParaEntidadeNegocio(ProdutoJpa produtoJpa) throws BusinessRulesExceptions {
        return new Produto(produtoJpa.getId(), produtoJpa.getNome(), produtoJpa.getDescricao(), produtoJpa.getPreco(),
                produtoJpa.getCategoria());
    }

    public static List<Produto> entidadesJpaParaEntidadesNegocio(List<ProdutoJpa> produtosJpa)
            throws BusinessRulesExceptions {
        List<Produto> produtos = new ArrayList<>();
        for (ProdutoJpa produtoJpa : produtosJpa) {
            Produto produto = entidadeJpaParaEntidadeNegocio(produtoJpa);
            produtos.add(produto);
        }
        return produtos;
    }

}
