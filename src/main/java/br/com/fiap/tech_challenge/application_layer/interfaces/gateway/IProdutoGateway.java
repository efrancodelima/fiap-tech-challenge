package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.ResourceNotFoundException;

public interface IProdutoGateway {

    public Produto gravarProduto(Produto produto) throws Exception;

    public void atualizarProduto(Produto produto) throws ResourceNotFoundException, Exception;

    public void removerProduto(long codigoProduto) throws ResourceNotFoundException, Exception;

    public Produto buscarProduto(long codigoProduto) throws ResourceNotFoundException, Exception;

    public List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria)
            throws ResourceNotFoundException, Exception;

}
