package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import java.util.List;

import br.com.fiap.tech_challenge.business_layer.entities.Produto;
import br.com.fiap.tech_challenge.business_layer.entities.enums.CategoriaProduto;

public interface IProdutoGateway {

    Produto gravarProduto(Produto produto) throws Exception;

    void atualizarProduto(Produto produto) throws Exception;

    void removerProduto(long codigoProduto) throws Exception;

    Produto buscarProduto(long codigoProduto) throws Exception;

    List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria) throws Exception;

    boolean produtoExiste(long codigoProduto) throws Exception;

}
