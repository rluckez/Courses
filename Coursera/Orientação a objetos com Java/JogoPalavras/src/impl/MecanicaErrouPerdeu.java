package impl;

import interfaces.MecanicaDoJogo;

/**
 * Implementação de MecanicaDoJogo que faz com que o jogo acabe após o usuário errar uma palavra
 * @author Rafael Luckez
 */
public class MecanicaErrouPerdeu extends MecanicaDoJogo{

	//Atributo responsável por armazenar a inforção referente ao fim do jogo
	private boolean perdeu = false;
	
	/**
	 * Método que indica se o jogo acabou ou não
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
	 * Retorna a pontuação do usuário de acordo com seu desempenho
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
		return "Você está jogando a versão morte súbita." + System.lineSeparator() +  
				"Você perderá ao errar uma palavra.";
	}

}
