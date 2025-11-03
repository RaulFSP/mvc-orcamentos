package io.github.app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteDto(
		Long id,
		@NotBlank(message = "o nome do cliente não pode estar vazio") 
		@Pattern(regexp = "\\D\\w+",message = "o nome de um cliente deve começar com uma letra")
		String name,
		Boolean active
		) {

}
