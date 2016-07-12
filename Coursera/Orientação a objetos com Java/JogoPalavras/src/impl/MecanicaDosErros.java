package impl;

import interfaces.MecanicaDoJogo;

/**
 * Implementação de MecanicaDoJogo que faz com que o jogo acabe após o usuário errar 3 palavras
 * @author Rafael Luckez
 *
 */
public class MecanicaDosErros extends MecanicaDoJogo{

	/**
	 * Método responsável pro verificar se o usuário já errou 3 palavras e encerrar o jogo
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
		return "Você está jogando a versão dos 3 erros." + System.lineSeparator() +  
				"Você pode continuar jogando até errar 3 palavras";
	}

}
