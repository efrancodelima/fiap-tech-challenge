package br.com.fiap.tech_challenge.interface_layer.controllers.adapters.response_adapters;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.business_layer.entities.Produto;

public final class ProdutoResponseAdapter {

    public static ResponseEntity<Produto> adaptar(Produto produto, HttpStatus status) {
        return new ResponseEntity<>(produto, status);
    }

    public static ResponseEntity<List<Produto>> adaptar(List<Produto> produtos, HttpStatus status) {
        return new ResponseEntity<>(produtos, status);
    }

}
