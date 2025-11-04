package io.github.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.app.entities.Orcamento;
import io.github.app.entities.Orcamento.Situacao;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

	@Query("select o from Orcamento o left join fetch o.items where o.id = :id")
	Optional<Orcamento> findByIdWithItems(@Param("id")  Long id);
	
	List<Orcamento> findBySituacao(Situacao situacao);
}
