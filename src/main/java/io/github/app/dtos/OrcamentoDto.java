package io.github.app.dtos;

import java.math.BigDecimal;
import java.util.Set;

import io.github.app.entities.ItemOrcamento;
import io.github.app.entities.Orcamento.Situacao;
import jakarta.validation.constraints.NotNull;

public record OrcamentoDto(
		Long id,
		@NotNull(message = "o cliente não pode ser nulo")
		Long idCliente,
		Situacao situacao,
		Set<ItemOrcamento> items,
		BigDecimal precoTotal
		) {

}
