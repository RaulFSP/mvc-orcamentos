package io.github.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.app.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findAllByActiveTrue();
	Optional<Cliente> findByName(String name);
	
	@Query("select c from Cliente c left join fetch c.orcamentos where c.id = :id")
	Optional<Cliente> findByIdWithOrcamentos(@Param("id")Long id);
}
