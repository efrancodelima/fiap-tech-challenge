package br.com.fiap.tech_challenge.external_layer.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;

@RestController
@RequestMapping("/produtos")
public class ProdutoApi {

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarProduto(Produto novoProduto) {
        try {
            return new ResponseEntity<>("funfou", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
