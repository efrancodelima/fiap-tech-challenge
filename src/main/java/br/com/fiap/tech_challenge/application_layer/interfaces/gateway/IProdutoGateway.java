package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;

public interface IProdutoGateway {

    public Produto gravarProduto(Produto produto) throws Exception;

    public void atualizarProduto(Produto produto) throws Exception;

    public void removerProduto(long idProduto) throws Exception;

    public List<Produto> buscarPorCategoria(CategoriaProduto categoria) throws Exception;

}
