package br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;

public final class ProdutoResponseAdapter {

    public static ResponseEntity<String> adaptar(Produto produto, HttpStatus status) {
        Gson gson = new Gson();
        String corpoResposta = gson.toJson(produto);
        return new ResponseEntity<>(corpoResposta, status);
    }

    public static ResponseEntity<String> adaptar(Exception e, HttpStatus status) {
        String msg = "Erro ao processar a requisição! " + e.getMessage();
        return new ResponseEntity<>(msg, status);
    }

    public static ResponseEntity<String> adaptar(String msg, HttpStatus status) {
        return new ResponseEntity<>(msg, status);
    }

    public static ResponseEntity<String> adaptar(List<Produto> produtos, HttpStatus status) {
        Gson gson = new Gson();
        String corpoResposta = gson.toJson(produtos);
        return new ResponseEntity<>(corpoResposta, status);
    }

}
