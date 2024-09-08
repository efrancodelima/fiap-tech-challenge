package br.com.fiap.tech_challenge.interface_layer.gateways.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ClienteJpa;

public interface IClienteRepository extends JpaRepository<ClienteJpa, Long> {

    ClienteJpa findByCpf(long cpf);

    boolean existsByCpf(long cpf);

}
