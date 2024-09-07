package br.com.fiap.tech_challenge.external_layer.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech_challenge.external_layer.api.interfaces.IPedidoApi;

@RestController
@RequestMapping("/pedidos")
public class PedidoApi implements IPedidoApi {

    @Override
    public ResponseEntity<String> criarPedido(Long cpf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criarPedido'");
    }

    @Override
    public ResponseEntity<String> criarPedido() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criarPedido'");
    }

}
