package br.com.fiap.tech_challenge.core.application.exception.pedido;

public class ErroAoCadastrarPedidoException extends RuntimeException{
    public ErroAoCadastrarPedidoException(String message) {
        super(message);
    }
}
