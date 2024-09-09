package br.com.fiap.tech_challenge.interface_layer.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.business_layer.entities.Produto;
import br.com.fiap.tech_challenge.business_layer.entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ProdutoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.mappers.ProdutoMapper;
import br.com.fiap.tech_challenge.interface_layer.gateways.repositories.IProdutoRepository;

@Component
public class ProdutoGateway implements IProdutoGateway {

    // Atributos
    @Autowired
    private IProdutoRepository produtoJpaRepository;

    // Métodos públicos
    @Override
    public Produto gravarProduto(Produto produto) throws Exception {
        ProdutoJpa produtoJpa = ProdutoMapper.getProdutoJpa(produto);
        produtoJpa = produtoJpaRepository.save(produtoJpa);
        return ProdutoMapper.getProduto(produtoJpa);
    }

    @Override
    public void atualizarProduto(Produto produto) throws Exception {
        ProdutoJpa produtoJpa = ProdutoMapper.getProdutoJpa(produto);
        produtoJpaRepository.save(produtoJpa);
    }

    @Override
    public void removerProduto(long codigoProduto) throws Exception {
        produtoJpaRepository.deleteById(codigoProduto);
    }

    @Override
    public Produto buscarProduto(long codigoProduto) throws Exception {
        Optional<ProdutoJpa> produtoJpa = produtoJpaRepository.findById(codigoProduto);
        return produtoJpa.isPresent() ? ProdutoMapper.getProduto(produtoJpa.get()) : null;
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria) throws Exception {
        List<ProdutoJpa> produtosJpa = produtoJpaRepository.findByCategoria(categoria);
        return ProdutoMapper.getListProduto(produtosJpa);
    }

    @Override
    public boolean produtoJaExiste(long codigoProduto) {
        return produtoJpaRepository.existsById(codigoProduto);
    }

}
