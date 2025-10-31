package io.github.app.mappers;

import org.springframework.stereotype.Component;

import io.github.app.dtos.ClienteDto;
import io.github.app.entities.Cliente;

@Component
public class ClienteMapper {

	public ClienteDto toDto(Cliente cliente) {
		return new ClienteDto(cliente.getId(), cliente.getName(), cliente.getActive());
	}

	public Cliente fromDto(ClienteDto dto) {
		return Cliente.builder().id(dto.id()).name(dto.name()).active(dto.active()).build();
	}

	public Cliente updateFromDto(ClienteDto dto, Long id) {
		return Cliente.builder().id(id).name(dto.name()).active(dto.active()).build();
	}
}
