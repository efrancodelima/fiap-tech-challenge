package br.com.fiap.tech_challenge.application_layer.use_cases;

import java.util.ArrayList;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
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
    public Produto cadastrarProduto(Produto novoProduto) {
        return gateway.gravarProduto(novoProduto);
    }

    @Override
    public Produto editarProduto(Produto produtoEditado) {
        return gateway.atualizarProduto(produtoEditado);
    }

    @Override
    public void removerProduto(Produto produto) {
        gateway.removerProduto(produto);
    }

    @Override
    public ArrayList<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria) {
        return gateway.buscarPorCategoria(categoria);
    }
}
