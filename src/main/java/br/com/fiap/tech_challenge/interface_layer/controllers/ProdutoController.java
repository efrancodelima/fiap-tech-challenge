package br.com.fiap.tech_challenge.interface_layer.controllers;

import java.util.List;
import java.util.stream.Collectors;

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
import br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters.ExceptionResponseAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters.ProdutoResponseAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters.MessageResponseAdapter;

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
    public ResponseEntity<String> cadastrarProduto(ProdutoDto produtoDto) {

        Produto produto;

        try {
            produto = ProdutoRequestAdapter.adaptar(produtoDto);
            produto = produtoUseCase.cadastrarProduto(produto);
        } catch (Exception e) {
            return ExceptionResponseAdapter.adaptar(e);
        }

        return ProdutoResponseAdapter.adaptar(produto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> editarProduto(long codigo, ProdutoDto produtoDto) {

        Produto produto;

        try {
            produto = ProdutoRequestAdapter.adaptar(codigo, produtoDto);
            produtoUseCase.editarProduto(produto);
        } catch (Exception e) {
            return ExceptionResponseAdapter.adaptar(e);
        }

        return MessageResponseAdapter.adaptar("Produto editado com sucesso!");
    }

    @Override
    public ResponseEntity<String> removerProduto(long codigo) {
        try {
            produtoUseCase.removerProduto(codigo);
        } catch (Exception e) {
            return ExceptionResponseAdapter.adaptar(e);
        }
        return MessageResponseAdapter.adaptar("Produto removido com sucesso!");
    }

    @Override
    public ResponseEntity<String> buscarProdutosPorCategoria(String categoriaStr) {

        CategoriaProduto categoria;
        List<Produto> produtos;

        try {
            categoria = CategoriaProdutoRequestAdapter.adaptar(categoriaStr);
            produtos = produtoUseCase.buscarProdutosPorCategoria(categoria);
        } catch (Exception e) {
            return ExceptionResponseAdapter.adaptar(e);
        }

        return ProdutoResponseAdapter.adaptar(produtos);
    }

}
