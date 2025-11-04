package io.github.app.mappers;

import org.springframework.stereotype.Component;

import io.github.app.dtos.OrcamentoDto;
import io.github.app.entities.Orcamento;

@Component
public class OrcamentoMapper {

	public Orcamento fromDto(OrcamentoDto dto) {
		Orcamento orcamento = Orcamento.builder()
				.id(dto.id())
				.idCliente(dto.idCliente())
				.situacao(dto.situacao())
				.precoTotal(dto.precoTotal())
				.build();
		return orcamento;
	}

	public OrcamentoDto toDto(Orcamento orcamento) {
		return new OrcamentoDto(
				orcamento.getId(), 
				orcamento.getIdCliente(),
				orcamento.getSituacao(), 
				orcamento.getItems(),
				orcamento.getPrecoTotal());
	}
}
