package br.com.fiap.tech_challenge.interface_layer.controllers.response_adapters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class MessageResponseAdapter {

    public static ResponseEntity<String> adaptar(String msg) {
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
