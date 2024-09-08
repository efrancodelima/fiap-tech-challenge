package br.com.fiap.tech_challenge.interface_layer.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.use_cases.ProdutoUseCase;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.interface_layer.controllers.interfaces.IProdutoController;
import br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters.CategoriaProdutoRequestAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters.ProdutoRequestAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters.ProdutoResponseAdapter;
import br.com.fiap.tech_challenge.interface_layer.dtos.ProdutoDto;
import br.com.fiap.tech_challenge.interface_layer.gateways.ProdutoGateway;
import jakarta.annotation.PostConstruct;

@Component
public class ProdutoController implements IProdutoController {

    // Atributos
    @Autowired
    ProdutoGateway gateway;
    ProdutoUseCase produtoUseCase;

    // Método de inicialização
    // A partir de um atributo injetado, inicializa um atributo não injetado
    @PostConstruct
    private void init() {
        this.produtoUseCase = new ProdutoUseCase(gateway);
    }

    // Métodos públicos
    @Override
    public ResponseEntity<Produto> cadastrarProduto(ProdutoDto produtoDto) throws Exception {

        Produto produto = ProdutoRequestAdapter.adaptar(produtoDto);
        produto = produtoUseCase.cadastrarProduto(produto);
        return ProdutoResponseAdapter.adaptar(produto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Produto> editarProduto(long codigo, ProdutoDto produtoDto) throws Exception {

        Produto produto = ProdutoRequestAdapter.adaptar(codigo, produtoDto);
        produtoUseCase.editarProduto(produto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Produto> removerProduto(long codigo) throws Exception {

        produtoUseCase.removerProduto(codigo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Produto>> buscarProdutosPorCategoria(String categoriaStr) throws Exception {

        CategoriaProduto categoria = CategoriaProdutoRequestAdapter.adaptar(categoriaStr);
        List<Produto> produtos = produtoUseCase.buscarProdutosPorCategoria(categoria);
        return ProdutoResponseAdapter.adaptar(produtos);
    }

}
