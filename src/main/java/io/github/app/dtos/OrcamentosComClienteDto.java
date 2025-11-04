package io.github.app.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrcamentosComClienteDto(Long idOrcamento, String clienteNome, LocalDateTime dataCriado, BigDecimal precoTotal) {

}
