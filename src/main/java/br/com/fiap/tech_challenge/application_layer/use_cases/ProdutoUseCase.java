package br.com.fiap.tech_challenge.application_layer.use_cases;

import java.util.List;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.ResourceNotFoundException;
import br.com.fiap.tech_challenge.application_layer.interfaces.use_cases.IProdutoUseCase;

public class ProdutoUseCase implements IProdutoUseCase {

    // Atributos
    private IProdutoGateway gateway;

    // Construtor
    public ProdutoUseCase(IProdutoGateway gateway) {
        this.gateway = gateway;
    }

    // Métodos
    @Override
    public Produto cadastrarProduto(Produto novoProduto) throws Exception {
        return gateway.gravarProduto(novoProduto);
    }

    @Override
    public void editarProduto(Produto produtoEditado) throws ResourceNotFoundException, Exception {
        gateway.atualizarProduto(produtoEditado);

    }

    @Override
    public void removerProduto(long codigoProduto) throws ResourceNotFoundException, Exception {
        gateway.removerProduto(codigoProduto);
    }

    @Override
    public Produto buscarProdutoPorCodigo(long codigoProduto) throws ResourceNotFoundException, Exception {
        return gateway.buscarProduto(codigoProduto);
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria)
            throws ResourceNotFoundException, Exception {
        return gateway.buscarProdutosPorCategoria(categoria);
    }

}
