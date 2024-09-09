package br.com.fiap.tech_challenge.application_layer.use_cases;

import java.util.List;

import br.com.fiap.tech_challenge.application_layer.exceptions.ResourceNotFoundException;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.application_layer.use_cases.interfaces.IProdutoUseCase;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;

public class ProdutoUseCase implements IProdutoUseCase {

    // Atributos
    private IProdutoGateway gateway;

    // Construtor
    public ProdutoUseCase(IProdutoGateway gateway) {
        this.gateway = gateway;
    }

    // Métodos públicos
    @Override
    public Produto cadastrarProduto(Produto produto) throws Exception {

        Validar.notNull(produto, EnumApplicationExceptions.PRODUTO_NULO);

        return gateway.gravarProduto(produto);
    }

    @Override
    public void editarProduto(Produto produto) throws Exception {

        Validar.notNull(produto, EnumApplicationExceptions.PRODUTO_NULO);
        validarProdutoExiste(produto.getCodigo(), EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO);

        gateway.atualizarProduto(produto);
    }

    @Override
    public void removerProduto(Long codigoProduto) throws Exception {

        validarCodigoProduto(codigoProduto);
        validarProdutoExiste(codigoProduto, EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO);

        gateway.removerProduto(codigoProduto);
    }

    @Override
    public Produto buscarProduto(Long codigoProduto) throws Exception {

        validarCodigoProduto(codigoProduto);

        Produto produto = gateway.buscarProduto(codigoProduto);
        Validar.notNull(produto, EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO);

        return produto;
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria) throws Exception {

        Validar.notNull(categoria, EnumApplicationExceptions.CATEGORIA_NULA);

        List<Produto> produtos = gateway.buscarProdutosPorCategoria(categoria);
        Validar.listNotEmpty(produtos, EnumNotFoundExceptions.PRODUTO_LISTA_VAZIA);

        return produtos;
    }

    // Métodos privados
    public void validarCodigoProduto(Long codigoProduto) throws Exception {

        Validar.notNull(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_NULO);
        Validar.maiorQueZero(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_MIN);
    }

    private void validarProdutoExiste(long codigoProduto, EnumNotFoundExceptions excecao) throws Exception {

        boolean produtoJaExiste = gateway.produtoJaExiste(codigoProduto);
        if (!produtoJaExiste) {
            throw new ResourceNotFoundException(excecao.getMensagem());
        }
    }
}
