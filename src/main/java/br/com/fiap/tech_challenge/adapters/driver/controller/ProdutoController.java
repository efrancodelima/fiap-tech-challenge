package br.com.fiap.tech_challenge.adapters.driver.controller;


import br.com.fiap.tech_challenge.adapters.driver.controller.mapper.ProdutoDTOMapper;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarProdutoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarProdutoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.swagger.ProdutoSwaggerInterface;
import br.com.fiap.tech_challenge.core.domain.model.Produto;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.core.domain.ports.in.ProdutoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/produtos")
public class ProdutoController implements ProdutoSwaggerInterface {

    private final ProdutoServicePort produtoServicePort;
    private final ProdutoDTOMapper produtoDTOMapper;

    @Autowired
    public ProdutoController(ProdutoServicePort service, ProdutoDTOMapper produtoDTOMapper) {
        this.produtoServicePort = service;
        this.produtoDTOMapper = produtoDTOMapper;
    }

    @Override
    public ResponseEntity<String> cadastrarProduto(CadastrarProdutoDTO cadastrar) {
        Produto produto = produtoDTOMapper.cadastrarToProdutoDTO(cadastrar);
        produtoServicePort.cadastrarProduto(produto);
        return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> consultaPorCategoria(CategoriaProduto categoriaProduto) {
        List<Produto> produtosPorCategoria = produtoServicePort.buscarProdutosPorCategoria(categoriaProduto);
        return new ResponseEntity<>(produtosPorCategoria, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> atualizaInformacoesProduto(AtualizarProdutoDTO atualizar) {
        Produto produto = produtoDTOMapper.atualizarToProdutoDTO(atualizar);
        produtoServicePort.atualizarProduto(produto);
        return new ResponseEntity<>("Produto atualizado com sucesso!", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> excluirProduto(Long id) {
        produtoServicePort.excluirProduto(id);
        return new ResponseEntity<>("Produto excluido com sucesso!", HttpStatus.OK);
    }
}
