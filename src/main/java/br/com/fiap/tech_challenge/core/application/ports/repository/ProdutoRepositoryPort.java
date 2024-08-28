package br.com.fiap.tech_challenge.core.application.ports.repository;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositoryPort {
    void deleteById(Long id);

    void save(ProdutoEntity produto);

    Optional<ProdutoEntity> findById(Long produtoId);

    List<ProdutoEntity> findProdutosByCategoria(CategoriaProduto categoriaProduto);
}
