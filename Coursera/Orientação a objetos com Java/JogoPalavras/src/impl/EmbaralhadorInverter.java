package impl;

import exception.PalavraInvalidaException;
import interfaces.Embaralhador;

/**
 * Implementa��o do Embaralhador que embaralha a palavra apenas invertendo sua ordem de letras
 * @author Rafael Luckez
 *
 */
public class EmbaralhadorInverter implements Embaralhador {

	/**
	 * Inverte as letras da palavra
	 * @throws PalavraInvalidaException A palavra � uma sequ�ncia que n�o pode ser embaralhada, exeplo: 'aaa', '333'
	 */
	@Override
	public String embaralhar(String palavra) throws PalavraInvalidaException {
		StringBuilder sb = new StringBuilder(palavra);
		String palavraEmbaralhada = sb.reverse().toString();
		if(palavra.equals(palavraEmbaralhada)){
			throw new PalavraInvalidaException();
		}else{
			return palavraEmbaralhada;
		}
		
	}

}
