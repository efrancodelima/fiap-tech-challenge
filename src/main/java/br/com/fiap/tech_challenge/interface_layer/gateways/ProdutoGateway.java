package br.com.fiap.tech_challenge.interface_layer.gateways;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.ProdutoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_mappers.ProdutoMapper;
import br.com.fiap.tech_challenge.interface_layer.gateways.repositories.IProdutoJpaRepository;

@Component
public class ProdutoGateway implements IProdutoGateway {

    // Atributos
    @Autowired
    private IProdutoJpaRepository produtoJpaRepository;

    // Métodos públicos
    @Override
    public Produto gravarProduto(Produto produto) throws Exception {
        ProdutoJpa produtoJpa = converterParaEntidadeJpa(produto);
        produtoJpa = produtoJpaRepository.save(produtoJpa);
        return converterParaEntidadeNegocio(produtoJpa);
    }

    @Override
    public void atualizarProduto(Produto produto) throws Exception {
        // O repositório JPA usa o método save() para insert e update
        gravarProduto(produto);
    }

    @Override
    public void removerProduto(Produto produto) throws Exception {
        ProdutoJpa produtoJpa = converterParaEntidadeJpa(produto);
        produtoJpaRepository.delete(produtoJpa);
    }

    @Override
    public List<Produto> buscarPorCategoria(CategoriaProduto categoria) throws Exception, Exception {
        List<ProdutoJpa> produtosJpa = produtoJpaRepository.findByCategoria(categoria);
        return converterParaEntidadesNegocio(produtosJpa);
    }

    // Métodos privados
    private ProdutoJpa converterParaEntidadeJpa(Produto produto) {
        return ProdutoMapper.entidadeNegocioParaEntidadeJpa(produto);
    }

    private Produto converterParaEntidadeNegocio(ProdutoJpa produtoJpa) throws Exception {
        return ProdutoMapper.entidadeJpaParaEntidadeNegocio(produtoJpa);
    }

    private List<Produto> converterParaEntidadesNegocio(List<ProdutoJpa> produtosJpa) throws Exception {
        return ProdutoMapper.entidadesJpaParaEntidadesNegocio(produtosJpa);
    }

}
