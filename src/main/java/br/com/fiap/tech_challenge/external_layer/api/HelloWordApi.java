package br.com.fiap.tech_challenge.external_layer.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordApi {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

}
