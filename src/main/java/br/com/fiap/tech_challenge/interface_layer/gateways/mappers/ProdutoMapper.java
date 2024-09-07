package br.com.fiap.tech_challenge.interface_layer.gateways.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ProdutoJpa;

public final class ProdutoMapper {

    // Métodos públicos
    public static ProdutoJpa mapperParaEntidadeJpa(Produto produto) {
        return new ProdutoJpa(produto.getCodigo(), produto.getNome(), produto.getDescricao(), produto.getPreco(),
                produto.getCategoria());
    }

    public static Produto mapperParaEntidadeNegocio(ProdutoJpa produtoJpa) throws Exception {
        return new Produto(produtoJpa.getId(), produtoJpa.getNome(), produtoJpa.getDescricao(), produtoJpa.getPreco(),
                produtoJpa.getCategoria());
    }

    public static List<Produto> mapperParaEntidadesNegocio(List<ProdutoJpa> produtosJpa)
            throws Exception {
        List<Produto> produtos = new ArrayList<>();
        for (ProdutoJpa produtoJpa : produtosJpa) {
            Produto produto = mapperParaEntidadeNegocio(produtoJpa);
            produtos.add(produto);
        }
        return produtos;
    }

}
