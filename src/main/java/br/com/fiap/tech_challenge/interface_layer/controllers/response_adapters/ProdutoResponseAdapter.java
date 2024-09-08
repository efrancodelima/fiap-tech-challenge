package br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;

public final class ProdutoResponseAdapter {

    public static ResponseEntity<Produto> adaptar(Produto produto, HttpStatus status) {
        return new ResponseEntity<>(produto, status);
    }

    public static ResponseEntity<List<Produto>> adaptar(List<Produto> produtos) {

        if (produtos.size() > 0) {
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
