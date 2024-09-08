package br.com.fiap.tech_challenge.interface_layer.gateways.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.PedidoJpa;

public interface IPedidoRepository extends JpaRepository<PedidoJpa, Long> {

}
