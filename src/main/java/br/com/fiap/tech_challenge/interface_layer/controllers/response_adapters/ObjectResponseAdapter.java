package br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

public class ObjectResponseAdapter {

    public static ResponseEntity<String> adaptar(Object objeto, HttpStatus status) {
        Gson gson = new Gson();
        String corpoResposta = gson.toJson(objeto);
        return new ResponseEntity<>(corpoResposta, status);
    }

    public static ResponseEntity<String> adaptar(List<Object> objetos) {

        if (objetos.size() > 0) {
            Gson gson = new Gson();
            String corpoResposta = gson.toJson(objetos);
            return new ResponseEntity<>(corpoResposta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
