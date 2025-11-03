package io.github.app.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ItemDto(
		Long id,
		@NotBlank(message = "o nome do item não pode estar vazio") String name,
		@PositiveOrZero(message = "o valor não pode ser negativo") BigDecimal preco,
		Boolean active
		) {

}
