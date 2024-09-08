package br.com.fiap.tech_challenge.interface_layer.controllers.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.interface_layer.dtos.ProdutoDto;

public interface IProdutoController {

    ResponseEntity<Produto> cadastrarProduto(ProdutoDto produtoDto) throws Exception;

    ResponseEntity<Produto> editarProduto(long codigo, ProdutoDto produtoDto) throws Exception;

    ResponseEntity<Produto> removerProduto(long codigo) throws Exception;

    ResponseEntity<List<Produto>> buscarProdutosPorCategoria(String categoriaStr) throws Exception;

}
