package impl;

import interfaces.MecanicaDoJogo;

/**
 * Implementa��o de MecanicaDoJogo que faz com que o jogo acabe ap�s o usu�rio errar uma palavra
 * @author Rafael Luckez
 */
public class MecanicaErrouPerdeu extends MecanicaDoJogo{

	//Atributo respons�vel por armazenar a infor��o referente ao fim do jogo
	private boolean perdeu = false;
	
	/**
	 * M�todo que indica se o jogo acabou ou n�o
	 */
	public boolean acabou() {
		return perdeu;
	}

	/**
	 * Realiza uma rodada do jogo
	 */
	public void rodada(boolean acertou){
		if(acertou){
			acertos++;
		}else{
			perdeu = true;
			erros++;
		}
		qtdPalavras++;
	}

	/**
	 * Retorna a pontua��o do usu�rio de acordo com seu desempenho
	 */
	@Override
	public int getPontos() {
		pontos = acertos * 10;
		return pontos;
	}
	
	/**
	 * Retorna as regras do jogo, explicando seu funcionamento
	 */
	@Override
	public String getJogoInfo() {
		return "Voc� est� jogando a vers�o morte s�bita." + System.lineSeparator() +  
				"Voc� perder� ao errar uma palavra.";
	}

}
