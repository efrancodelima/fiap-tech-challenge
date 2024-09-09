package br.com.fiap.tech_challenge.application_layer.use_cases.interfaces;

import java.util.List;

import br.com.fiap.tech_challenge.business_layer.entities.Produto;
import br.com.fiap.tech_challenge.business_layer.entities.enums.CategoriaProduto;

public interface IProdutoUseCase {

    Produto cadastrarProduto(Produto novoProduto) throws Exception;

    void editarProduto(Produto produtoEditado) throws Exception;

    void removerProduto(Long codigoProduto) throws Exception;

    Produto buscarProduto(Long codigoProduto) throws Exception;

    List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria) throws Exception;
}
