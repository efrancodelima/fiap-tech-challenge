package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {
}
