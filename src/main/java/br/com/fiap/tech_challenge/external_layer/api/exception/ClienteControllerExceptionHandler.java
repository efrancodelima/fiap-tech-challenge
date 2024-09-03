package br.com.fiap.tech_challenge.external_layer.api.exception;

import br.com.fiap.tech_challenge.core.application.exception.cliente.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClienteControllerExceptionHandler {

    @ExceptionHandler(ClienteJaCadastradoException.class)
    public ResponseEntity<String> clienteJaCadastradoException(ClienteJaCadastradoException clienteJaCadastradoException) {
        return new ResponseEntity<>(clienteJaCadastradoException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<String> clienteNaoEncontradoException(ClienteNaoEncontradoException clienteNaoEncontradoException) {
        return new ResponseEntity<>(clienteNaoEncontradoException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErroAoCadastrarClienteException.class)
    public ResponseEntity<String> erroAoCadastrarClienteException(ErroAoCadastrarClienteException erroAoCadastrarClienteException) {
        return new ResponseEntity<>(erroAoCadastrarClienteException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErroAoAtualizarAsInformacoesDoClienteException.class)
    public ResponseEntity<String> erroAoAtualizarAsInformacoesDoClienteException(ErroAoAtualizarAsInformacoesDoClienteException erroAoAtualizarAsInformacoesDoClienteException) {
        return new ResponseEntity<>(erroAoAtualizarAsInformacoesDoClienteException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErroAoExcluirClienteException.class)
    public ResponseEntity<String> erroAoExcluirClienteException(ErroAoExcluirClienteException erroAoExcluirClienteException) {
        return new ResponseEntity<>(erroAoExcluirClienteException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
