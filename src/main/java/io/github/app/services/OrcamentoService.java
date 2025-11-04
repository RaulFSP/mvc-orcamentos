package io.github.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.app.dtos.OrcamentoDto;
import io.github.app.entities.Cliente;
import io.github.app.entities.Orcamento;
import io.github.app.entities.Orcamento.Situacao;
import io.github.app.mappers.OrcamentoMapper;
import io.github.app.repositories.ClienteRepository;
import io.github.app.repositories.OrcamentoRepository;

@Service
public class OrcamentoService {

	private final OrcamentoMapper orcamentoMapper;
	private final OrcamentoRepository orcamentoRepository;
	private final ClienteRepository clienteRepository;

	private OrcamentoService(
			OrcamentoMapper orcamentoMapper,
			OrcamentoRepository orcamentoRepository,
			ClienteRepository clienteRepository) {
		this.orcamentoMapper = orcamentoMapper;
		this.orcamentoRepository = orcamentoRepository;
		this.clienteRepository = clienteRepository;
	}

	public List<OrcamentoDto> findAllSituacaoAberto() {
		return orcamentoRepository.findBySituacao(Situacao.ABERTO)
				.parallelStream().map(m -> orcamentoMapper.toDto(m)).toList();
	}
	public void createOrcamento(OrcamentoDto dto) {
		Orcamento orcamento = orcamentoMapper.fromDto(dto);
		Cliente cliente = clienteRepository
				.findByIdWithOrcamentos(orcamento.getIdCliente()).orElseThrow();
		cliente.getOrcamentos().add(orcamento);
		clienteRepository.save(cliente);
	}
}
