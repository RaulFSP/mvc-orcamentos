package io.github.app.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.github.app.dtos.OrcamentosComClienteDto;
import io.github.app.entities.Orcamento.Situacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class DtoSpecificRepository {

	@PersistenceContext
	private EntityManager em;

	public List<OrcamentosComClienteDto> orcamentosComCliente() {
		return em.createQuery(
				"select new io.github.app.dtos.OrcamentosComClienteDto(o.id,c.name,o.dateCreated,o.precoTotal) from Cliente c join c.orcamentos o",
				OrcamentosComClienteDto.class).getResultList();
	}

	public List<OrcamentosComClienteDto> orcamentosComCliente(Situacao situacao){
		String jpql = """
				select new io.github.app.dtos.OrcamentosComClienteDto(o.id,c.name,o.dateCreated,o.precoTotal) from Cliente c join c.orcamentos o where o.situacao = :situacao
				""";		
		TypedQuery<OrcamentosComClienteDto> query = em.createQuery(jpql,OrcamentosComClienteDto.class);
				query.setParameter("situacao", situacao);
				return query.getResultList();
		
	}
}
