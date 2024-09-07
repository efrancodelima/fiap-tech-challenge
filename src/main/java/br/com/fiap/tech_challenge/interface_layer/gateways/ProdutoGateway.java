package br.com.fiap.tech_challenge.interface_layer.gateways;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ProdutoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.ResourceNotFoundException;
import br.com.fiap.tech_challenge.interface_layer.gateways.mappers.ProdutoMapper;
import br.com.fiap.tech_challenge.interface_layer.gateways.repositories.IProdutoRepository;

@Component
public class ProdutoGateway implements IProdutoGateway {

    // Atributos
    @Autowired
    private IProdutoRepository produtoJpaRepository;
    private final String PRODUTO_NAO_ENCONTRADO = "Não foi encontrado nenhum produto para o código informado.";

    // Métodos públicos
    @Override
    public Produto gravarProduto(Produto produto) throws Exception {
        ProdutoJpa produtoJpa = ProdutoMapper.mapearParaEntidadeJpa(produto);
        produtoJpa = produtoJpaRepository.save(produtoJpa);
        return ProdutoMapper.mapearParaEntidadeNegocio(produtoJpa);
    }

    @Override
    public void atualizarProduto(Produto produto) throws Exception {
        if (!produtoJpaRepository.existsById(produto.getCodigo())) {
            throw new ResourceNotFoundException(PRODUTO_NAO_ENCONTRADO);
        }
        ProdutoJpa produtoJpa = ProdutoMapper.mapearParaEntidadeJpa(produto);
        produtoJpaRepository.save(produtoJpa);
    }

    @Override
    public void removerProduto(long codigo) throws ResourceNotFoundException, Exception {
        if (!produtoJpaRepository.existsById(codigo)) {
            throw new ResourceNotFoundException(PRODUTO_NAO_ENCONTRADO);
        }
        produtoJpaRepository.deleteById(codigo);
    }

    @Override
    public List<Produto> buscarPorCategoria(CategoriaProduto categoria)
            throws ResourceNotFoundException, Exception {
        List<ProdutoJpa> produtosJpa = produtoJpaRepository.findByCategoria(categoria);
        return ProdutoMapper.mapearParaEntidadesNegocio(produtosJpa);
    }

}
