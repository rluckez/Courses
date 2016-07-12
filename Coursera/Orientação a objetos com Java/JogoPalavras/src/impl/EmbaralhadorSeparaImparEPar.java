package impl;

import interfaces.Embaralhador;

import java.util.ArrayList;
import java.util.List;

import exception.PalavraInvalidaException;

/**
 * Implementação do Embaralhador que embaralha aleatoriamente a palavra
 * @author Rafael Luckez
 *
 */
public class EmbaralhadorSeparaImparEPar implements Embaralhador {

	/**
	 * Implementação do método embarahar que embaralha a palavra de forma aleatória
	 * @throws PalavraInvalidaException A palavra é uma sequência que não pode ser embaralhada, exeplo: 'aaa', '333'
	 */
	@Override
	public String embaralhar(String palavra) throws PalavraInvalidaException {
		char[] caracteres = palavra.toCharArray();
		List<Character> impar = new ArrayList<>();
		List<Character> par = new ArrayList<>();
		for (int i = 0; i < caracteres.length; i++) {
			if(i % 2 == 0){
				par.add((Character) caracteres[i]); 
			}else{
				impar.add((Character) caracteres[i]);
			}
		}
		
		String palavraEmbaralhada = "";
		for(int i = 0; i < par.size(); i++){
			palavraEmbaralhada += par.get(i);
		}
		for(int i = 0; i < impar.size(); i++){
			palavraEmbaralhada += impar.get(i);
		}
		
		if(palavra.equals(palavraEmbaralhada)){
			throw new PalavraInvalidaException();
		}else{
			return palavraEmbaralhada;
		}
	}

}
