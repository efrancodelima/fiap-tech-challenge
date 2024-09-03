package br.com.fiap.tech_challenge.application_layer.interfaces.use_cases;

import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;

public interface IProdutoUseCase {

    Produto cadastrarProduto(Produto novoProduto) throws BusinessRulesExceptions, Exception;

    Produto editarProduto(Produto produtoEditado) throws BusinessRulesExceptions, Exception;

    void removerProduto(Produto produto) throws BusinessRulesExceptions, Exception;

    List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria) throws BusinessRulesExceptions, Exception;
}
