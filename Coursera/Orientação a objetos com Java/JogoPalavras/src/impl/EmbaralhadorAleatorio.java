package impl;

import interfaces.Embaralhador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exception.PalavraInvalidaException;

/**
 * Implementa��o do Embaralhador que embaralha aleatoriamente a palavra
 * @author Rafael Luckez
 *
 */
public class EmbaralhadorAleatorio implements Embaralhador {

	/**
	 * Implementa��o do m�todo embarahar que embaralha a palavra de forma aleat�ria
	 * @throws PalavraInvalidaException A palavra � uma sequ�ncia que n�o pode ser embaralhada, exeplo: 'aaa', '333'
	 */
	@Override
	public String embaralhar(String palavra) throws PalavraInvalidaException {
		char[] caracteres = palavra.toCharArray();
		List<Character> lista = new ArrayList<>();
		for (int i = 0; i < caracteres.length; i++) {
			lista.add((Character) caracteres[i]); 
		}
			
		Collections.shuffle(lista);
		String palavraEmbaralhada = "";
		for(int i = 0; i < lista.size(); i++){
			palavraEmbaralhada += lista.get(i);
		}
		if(palavra.equals(palavraEmbaralhada)){
			throw new PalavraInvalidaException();
		}else{
			return palavraEmbaralhada;
		}
	}

}
