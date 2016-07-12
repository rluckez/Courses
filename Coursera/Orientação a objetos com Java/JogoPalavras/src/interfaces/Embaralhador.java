package interfaces;

import exception.PalavraInvalidaException;

/**
 * Interface responsável pelo procedimento de embaralhar uma palavra
 * @author Rafael Luckez
 *
 */
public interface Embaralhador {

	public String embaralhar(String palavra) throws PalavraInvalidaException;
	
}
