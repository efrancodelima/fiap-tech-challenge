package br.com.fiap.tech_challenge.interface_layer.gateways.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ProdutoJpa;

public interface IProdutoRepository extends JpaRepository<ProdutoJpa, Long> {

    List<ProdutoJpa> findByCategoria(CategoriaProduto categoria);

}
