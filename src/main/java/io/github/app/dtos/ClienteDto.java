package io.github.app.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClienteDto(
		Long id,
		@NotBlank(message = "o nome do cliente não pode estar vazio") String name,
		Boolean active
		) {

}
