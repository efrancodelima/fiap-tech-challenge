package br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters;

import java.math.BigDecimal;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesException;
import br.com.fiap.tech_challenge.interface_layer.dtos.ProdutoDto;

public final class ProdutoRequestAdapter {

    public static Produto adaptar(ProdutoDto produtoDto) throws BusinessRulesException {
        String nome = produtoDto.getNome();
        String descricao = produtoDto.getDescricao();
        BigDecimal preco = produtoDto.getPreco();
        CategoriaProduto categoria = CategoriaProdutoRequestAdapter.adaptar(produtoDto.getCategoria());
        return new Produto(nome, descricao, preco, categoria);
    }

    public static Produto adaptar(long id, ProdutoDto produtoDto) throws BusinessRulesException {
        var produto = adaptar(produtoDto);
        produto.setCodigo(id);
        return produto;
    }

}
