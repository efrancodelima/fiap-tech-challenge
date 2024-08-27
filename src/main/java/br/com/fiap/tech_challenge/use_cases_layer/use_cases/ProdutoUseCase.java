package br.com.fiap.tech_challenge.use_cases_layer.use_cases;

import java.util.ArrayList;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.use_cases_layer.repository.ProdutoRepository;

public class ProdutoUseCase {

    public static Produto cadastrarProduto(ProdutoRepository repository, Produto novoProduto) {
        return repository.gravarProduto(novoProduto);
    }

    public static Produto editarProduto(ProdutoRepository repository, Produto produtoEditado) {
        return repository.alterarProduto(produtoEditado);
    }

    public static void removerProduto(ProdutoRepository repository, Produto produto) {
        repository.removerProduto(produto);
    }

    public static ArrayList<Produto> buscarProdutosPorCategoria(ProdutoRepository repository,
            CategoriaProduto categoria) {
        return repository.buscarPorCategoria(categoria);
    }
}
