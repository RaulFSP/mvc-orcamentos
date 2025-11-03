package io.github.app.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import io.github.app.entities.ItemOrcamento;
import io.github.app.entities.Orcamento.Situacao;

public record OrcamentoDto(
		Long id,
		Long idCliente,
		Situacao situacao,
		Set<ItemOrcamento> items,
		BigDecimal precoTotal
		) {

}
