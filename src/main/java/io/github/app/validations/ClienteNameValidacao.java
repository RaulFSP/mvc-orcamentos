package io.github.app.validations;

import org.springframework.stereotype.Component;

import io.github.app.entities.Cliente;
import io.github.app.exceptions.ClienteValidacaoException;

@Component
public class ClienteNameValidacao implements ClienteValidations {

	@Override
	public void validar(Cliente cliente) throws ClienteValidacaoException{
		if(cliente.getName().length() < 3) {
			throw new ClienteValidacaoException("Este nome de cliente não é válido!");
		}

	}

}
