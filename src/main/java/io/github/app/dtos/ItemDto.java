package io.github.app.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ItemDto(
		Long id,
		@NotBlank String name,
		@PositiveOrZero BigDecimal preco,
		Boolean active,
		Long version) {

}
