package br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;

public final class ProdutoResponseAdapter {

    public static ResponseEntity<String> adaptar(Produto produto, HttpStatus status) {
        return ObjectResponseAdapter.adaptar(produto, status);
    }

    public static ResponseEntity<String> adaptar(List<Produto> produtos) {

        var produtosObj = produtos.stream()
                .map(produto -> (Object) produto)
                .collect(Collectors.toList());

        return ObjectResponseAdapter.adaptar(produtosObj);
    }

}
