package br.com.fiap.tech_challenge.interface_layer.controllers.adapters.request_adapters;

import java.math.BigDecimal;

import br.com.fiap.tech_challenge.business_layer.entities.Produto;
import br.com.fiap.tech_challenge.business_layer.entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.ProdutoDto;

public final class ProdutoRequestAdapter {

    public static Produto adaptar(ProdutoDto produtoDto) throws Exception {

        String nome = produtoDto.getNome().trim();
        String descricao = produtoDto.getDescricao();
        BigDecimal preco = produtoDto.getPreco();
        CategoriaProduto categoria = CategoriaProduto.fromString(produtoDto.getCategoria());
        return new Produto(nome, descricao, preco, categoria);
    }

    public static Produto adaptar(long codigo, ProdutoDto produtoDto) throws Exception {

        String nome = produtoDto.getNome().trim();
        String descricao = produtoDto.getDescricao();
        BigDecimal preco = produtoDto.getPreco();
        CategoriaProduto categoria = CategoriaProduto.fromString(produtoDto.getCategoria());
        return new Produto(codigo, nome, descricao, preco, categoria);
    }

}
