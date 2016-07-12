package impl;

import interfaces.MecanicaDoJogo;

/**
 * Implementa��o de MecanicaDoJogo que faz com que o jogo acabe ap�s o usu�rio errar 3 palavras
 * @author Rafael Luckez
 *
 */
public class MecanicaDosErros extends MecanicaDoJogo{

	/**
	 * M�todo respons�vel pro verificar se o usu�rio j� errou 3 palavras e encerrar o jogo
	 */
	public boolean acabou() {
		
		if(erros >= 3){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Realiza uma rodada do jogo
	 */
	public void rodada(boolean acertou){
		if(acertou){
			acertos++;
		}else{
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
		return "Voc� est� jogando a vers�o dos 3 erros." + System.lineSeparator() +  
				"Voc� pode continuar jogando at� errar 3 palavras";
	}

}
