package br.com.fiap.tech_challenge.application_layer.use_cases;

import java.util.List;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
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
    public void editarProduto(Produto produtoEditado) throws Exception {
        gateway.atualizarProduto(produtoEditado);

    }

    @Override
    public void removerProduto(long codigoProduto) throws Exception {
        gateway.removerProduto(codigoProduto);
    }

    @Override
    public Produto buscarProdutoPorCodigo(long codigoProduto) throws Exception {
        return gateway.buscarProduto(codigoProduto);
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria) throws Exception {
        return gateway.buscarProdutosPorCategoria(categoria);
    }

}
