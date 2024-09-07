package br.com.fiap.tech_challenge.interface_layer.controllers.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.interface_layer.dtos.ProdutoDto;

public interface IProdutoController {

    ResponseEntity<String> cadastrarProduto(ProdutoDto produtoDto);

    ResponseEntity<String> editarProduto(long codigo, ProdutoDto produtoDto);

    ResponseEntity<String> removerProduto(long codigo);

    ResponseEntity<String> buscarProdutosPorCategoria(String categoriaStr);

}
