package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.ProdutoRepository;
import br.com.fiap.tech_challenge.core.application.ports.repository.ProdutoRepositoryPort;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProdutoRepositoryAdapter implements ProdutoRepositoryPort {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoRepositoryAdapter(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public void save(ProdutoEntity produto) {
        produtoRepository.save(produto);
    }

    @Override
    public Optional<ProdutoEntity> findById(Long produtoId) {
        return produtoRepository.findById(produtoId);
    }

    @Override
    public List<ProdutoEntity> findProdutosByCategoria(CategoriaProduto categoriaProduto) {
        return produtoRepository.findAllByCategoriaProduto(categoriaProduto);
    }
}
