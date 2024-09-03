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
        try {
            return gateway.gravarProduto(novoProduto);
        } catch (Exception e) {
            String msg = "Erro ao cadastrar o produto! ";
            throw new Exception(msg + e.getMessage());
        }
    }

    @Override
    public void editarProduto(Produto produtoEditado) throws Exception {
        try {
            gateway.atualizarProduto(produtoEditado);
        } catch (Exception e) {
            String msg = "Erro ao editar o produto! ";
            throw new Exception(msg + e.getMessage());
        }
    }

    @Override
    public void removerProduto(Produto produto) throws Exception {
        try {
            gateway.removerProduto(produto);
        } catch (Exception e) {
            String msg = "Erro ao remover o produto! ";
            throw new Exception(msg + e.getMessage());
        }
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria)
            throws Exception {
        try {
            return gateway.buscarPorCategoria(categoria);
        } catch (Exception e) {
            String msg = "Erro ao buscar os produtos! ";
            throw new Exception(msg + e.getMessage());
        }

    }
}
