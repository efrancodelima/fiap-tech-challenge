package br.com.fiap.tech_challenge.external_layer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech_challenge.external_layer.swagger.IProdutoSwagger;
import br.com.fiap.tech_challenge.interface_layer.controllers.ProdutoController;
import br.com.fiap.tech_challenge.interface_layer.dtos.ProdutoDto;

@RestController
@RequestMapping("/produtos")
public class ProdutoApi implements IProdutoSwagger {

    @Autowired
    private ProdutoController controller;

    @Override
    public ResponseEntity<String> cadastrarProduto(@RequestBody ProdutoDto produtoDto) {
        return controller.cadastrarProduto(produtoDto);
    }

    @Override
    public ResponseEntity<String> editarProduto(@PathVariable long codigo, @RequestBody ProdutoDto produtoDto) {
        return controller.editarProduto(codigo, produtoDto);
    }

    @Override
    public ResponseEntity<String> removerProduto(@PathVariable long codigo) {
        return controller.removerProduto(codigo);
    }

    @Override
    public ResponseEntity<String> buscarProdutosPorCategoria(@PathVariable String categoria) {
        return controller.buscarProdutosPorCategoria(categoria);
    }
}
