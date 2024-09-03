package br.com.fiap.tech_challenge.interface_layer.gateways.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.ClienteJpa;

public interface IClienteJpaRepository extends JpaRepository<ClienteJpa, Long> {

    ClienteJpa findByCpf(long cpf);

}
