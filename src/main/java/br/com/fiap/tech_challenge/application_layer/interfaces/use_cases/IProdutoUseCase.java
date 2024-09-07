package br.com.fiap.tech_challenge.application_layer.interfaces.use_cases;

import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;

public interface IProdutoUseCase {

    Produto cadastrarProduto(Produto novoProduto) throws Exception;

    void editarProduto(Produto produtoEditado) throws Exception;

    void removerProduto(long idProduto) throws Exception;

    List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria) throws Exception;
}
