package br.com.fiap.tech_challenge.interface_layer.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ProdutoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.exceptions.MyNotFoundException;
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
    public void atualizarProduto(Produto produto) throws MyNotFoundException, Exception {
        if (!produtoJpaRepository.existsById(produto.getCodigo())) {
            throw new MyNotFoundException(PRODUTO_NAO_ENCONTRADO);
        }
        ProdutoJpa produtoJpa = ProdutoMapper.mapearParaEntidadeJpa(produto);
        produtoJpaRepository.save(produtoJpa);
    }

    @Override
    public void removerProduto(long codigoProduto) throws MyNotFoundException, Exception {
        if (!produtoJpaRepository.existsById(codigoProduto)) {
            throw new MyNotFoundException(PRODUTO_NAO_ENCONTRADO);
        }
        produtoJpaRepository.deleteById(codigoProduto);
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria)
            throws MyNotFoundException, Exception {
        List<ProdutoJpa> produtosJpa = produtoJpaRepository.findByCategoria(categoria);
        return ProdutoMapper.mapearParaEntidadesNegocio(produtosJpa);
    }

    @Override
    public Produto buscarProduto(long codigoProduto) throws MyNotFoundException, Exception {

        Optional<ProdutoJpa> produtoJpa = produtoJpaRepository.findById(codigoProduto);

        if (produtoJpa.isPresent()) {
            return ProdutoMapper.mapearParaEntidadeNegocio(produtoJpa.get());
        } else {
            throw new MyNotFoundException(PRODUTO_NAO_ENCONTRADO);
        }
    }

}
