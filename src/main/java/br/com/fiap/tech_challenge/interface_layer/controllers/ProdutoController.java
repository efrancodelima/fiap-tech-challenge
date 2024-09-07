package br.com.fiap.tech_challenge.interface_layer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.use_cases.ProdutoUseCase;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters.CategoriaProdutoAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters.ProdutoRequestAdapter;
import br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters.ProdutoResponseAdapter;
import br.com.fiap.tech_challenge.interface_layer.dtos.ProdutoDto;
import br.com.fiap.tech_challenge.interface_layer.gateways.ProdutoGateway;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;

@Component
public class ProdutoController {

    // Atributos
    @Autowired
    ProdutoGateway gateway;
    ProdutoUseCase useCase;

    // Método de inicialização
    // A partir de um atributo injetado, inicializa um atributo não injetado
    @PostConstruct
    private void init() {
        this.useCase = new ProdutoUseCase(gateway);
    }

    // Métodos públicos
    public ResponseEntity<String> cadastrarProduto(ProdutoDto produtoDto) {

        // Adapta a requisição
        Produto produto;
        try {
            produto = ProdutoRequestAdapter.adaptar(produtoDto);
        } catch (Exception e) {
            return ProdutoResponseAdapter.adaptar(e, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        // Chama o caso de uso
        try {
            produto = useCase.cadastrarProduto(produto);
        } catch (Exception e) {
            return ProdutoResponseAdapter.adaptar(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Retorna a resposta
        return ProdutoResponseAdapter.adaptar(produto, HttpStatus.CREATED);
    }

    public ResponseEntity<String> editarProduto(long codigo, ProdutoDto produtoDto) {

        // Adapta a requisição
        Produto produto;
        try {
            produto = ProdutoRequestAdapter.adaptar(codigo, produtoDto);
        } catch (Exception e) {
            return ProdutoResponseAdapter.adaptar(e, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        // Chama o caso de uso
        try {
            useCase.editarProduto(produto);
        } catch (ResourceNotFoundException e) {
            return ProdutoResponseAdapter.adaptar(e, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ProdutoResponseAdapter.adaptar(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Retorna a resposta
        return ProdutoResponseAdapter.adaptar("Produto editado com sucesso!", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> removerProduto(long codigo) {
        try {
            useCase.removerProduto(codigo);
        } catch (ResourceNotFoundException e) {
            return ProdutoResponseAdapter.adaptar(e, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ProdutoResponseAdapter.adaptar(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ProdutoResponseAdapter.adaptar("Produto removido com sucesso!", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> buscarProdutosPorCategoria(String categoriaStr) {
        // TODO: implementar
        CategoriaProduto categoria;
        try {
            categoria = CategoriaProdutoAdapter.adaptar(categoriaStr);

        } catch (Exception e) {
            return ProdutoResponseAdapter.adaptar(e, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        List<Produto> produtos;
        try {
            produtos = useCase.buscarProdutosPorCategoria(categoria);
        } catch (ResourceNotFoundException e) {
            return ProdutoResponseAdapter.adaptar(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ProdutoResponseAdapter.adaptar(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ProdutoResponseAdapter.adaptar(produtos, HttpStatus.OK);

    }

}
