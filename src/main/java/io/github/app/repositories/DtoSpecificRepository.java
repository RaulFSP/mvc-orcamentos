package io.github.app.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.github.app.dtos.OrcamentosComClienteDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DtoSpecificRepository {

	@PersistenceContext
	private EntityManager em;
	
	public List<OrcamentosComClienteDto> orcamentosComCliente(){
		return em.createQuery("select new io.github.app.dtos.OrcamentosComClienteDto(o.id,c.name,o.dateCreated,o.precoTotal) from Cliente c join c.orcamentos o",OrcamentosComClienteDto.class).getResultList();
	}
}
