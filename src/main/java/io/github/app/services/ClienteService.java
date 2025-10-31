package io.github.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.app.dtos.ClienteDto;
import io.github.app.mappers.ClienteMapper;
import io.github.app.repositories.ClienteRepository;
import io.github.app.validations.ClienteValidations;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	private final List<ClienteValidations> clienteValidations;
	private final ClienteMapper clienteMapper;
	private final ClienteRepository clienteRepository;

	public ClienteService(
			List<ClienteValidations> clienteValidations,
			ClienteMapper clienteMapper,
			ClienteRepository clienteRepository) {
		this.clienteValidations = clienteValidations;
		this.clienteMapper = clienteMapper;
		this.clienteRepository = clienteRepository;
	}

	public List<ClienteDto> findAll() {
		return clienteRepository.findAllByActiveTrue().parallelStream()
				.map(m -> clienteMapper.toDto(m)).toList();
	}

	public ClienteDto findById(long id) {
		var cliente = clienteRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("cliente não encontrado"));

		return clienteMapper.toDto(cliente);
	}

	public ClienteDto createCliente(ClienteDto dto) {
		var cliente = clienteMapper.fromDto(dto);
		cliente.setActive(true);
		cliente = clienteRepository.save(cliente);
		return clienteMapper.toDto(cliente);
	}
	
	@Transactional
	public void clienteDisable(Long id) {
		var cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("cliente não encontrado"));
		if(cliente.getActive()) {
			cliente.setActive(false);
		}
	}
	
	@Transactional
	public void clienteUpdate(Long id, ClienteDto dto) {
		var cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("cliente não encontrado"));
		cliente = clienteMapper.updateFromDto(dto,id);
		
	}
}
