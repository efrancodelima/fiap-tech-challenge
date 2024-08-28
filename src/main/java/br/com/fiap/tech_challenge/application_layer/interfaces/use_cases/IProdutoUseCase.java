package br.com.fiap.tech_challenge.application_layer.interfaces.use_cases;

import java.util.ArrayList;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;

public interface IProdutoUseCase {

    Produto cadastrarProduto(Produto novoProduto);

    Produto editarProduto(Produto produtoEditado);

    void removerProduto(Produto produto);

    ArrayList<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria);
}
