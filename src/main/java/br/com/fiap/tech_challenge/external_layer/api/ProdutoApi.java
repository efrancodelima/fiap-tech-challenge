package br.com.fiap.tech_challenge.external_layer.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.external_layer.api.interfaces.IProdutoApi;
import br.com.fiap.tech_challenge.interface_layer.controllers.ProdutoController;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.ProdutoDto;

@RestController
@RequestMapping("/produtos")
public class ProdutoApi implements IProdutoApi {

    @Autowired
    private ProdutoController controller;

    @Override
    public ResponseEntity<Produto> cadastrarProduto(ProdutoDto produtoDto) throws Exception {
        return controller.cadastrarProduto(produtoDto);
    }

    @Override
    public ResponseEntity<Produto> editarProduto(long codigo, ProdutoDto produtoDto) throws Exception {
        return controller.editarProduto(codigo, produtoDto);
    }

    @Override
    public ResponseEntity<Produto> removerProduto(long codigo) throws Exception {
        return controller.removerProduto(codigo);
    }

    @Override
    public ResponseEntity<List<Produto>> buscarProdutosPorCategoria(String categoria) throws Exception {
        return controller.buscarProdutosPorCategoria(categoria);
    }
}
