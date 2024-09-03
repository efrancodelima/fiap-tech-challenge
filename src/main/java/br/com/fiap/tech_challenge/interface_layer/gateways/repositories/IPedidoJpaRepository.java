package br.com.fiap.tech_challenge.interface_layer.gateways.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.PedidoJpa;

public interface IPedidoJpaRepository extends JpaRepository<PedidoJpa, Long> {

    @Query("SELECT p FROM PedidoJpa p WHERE p.statusPedido.status IN (:status)")
    List<PedidoJpa> listarPedidosPorStatusIn(@Param("status") List<StatusPedidoEnum> status);

}
