package impl;

import interfaces.MecanicaDoJogo;

/**
 * Implementação de MecanicaDoJogo que faz com que o jogo acabe após atingir um certo número de rodadas
 * @author Rafael Luckez
 *
 */
public class MecanicaDaQuantidade extends MecanicaDoJogo {

	//Atributo responsável por dizer qual é o número de rodadas que o jogo terá
	private int duracaoJogo;
	
	public MecanicaDaQuantidade() {
		duracaoJogo = 5;
	}
	
	/**
	 * Verifica se o jogo já atingiu o número máximo de rodadas, se sim, indica que o jogo chegou ao fim
	 */
	public boolean acabou() {
		if(getQtdPalavras() >= duracaoJogo){
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
		double result = ((double) acertos) / qtdPalavras;
		return Double.valueOf(result * 100).intValue();
	}

	/**
	 * Retorna as regras do jogo, explicando seu funcionamento
	 */
	@Override
	public String getJogoInfo() {
		return "Você está jogando a versão com quantidade de palavras." + System.lineSeparator() + "Sua pontuação será baseada em seus acertos. " + System.lineSeparator() + 
				"O jogo termina ao chegar numa determinada quantidade de palavras";
	}

	
}
