package io.github.app.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import io.github.app.entities.Orcamento.Situacao;
@Component
public class SituacaoConverter implements Converter<String, Situacao>{

	@Override
	public Situacao convert(String source) {

		if(source == null) {
			throw new IllegalArgumentException("não pode ser nulo");
		}
		try {
			return Situacao.valueOf(source.toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("Situacao inválida");
		}
		
	}

}
