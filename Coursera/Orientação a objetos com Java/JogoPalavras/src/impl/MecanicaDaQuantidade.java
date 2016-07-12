package impl;

import interfaces.MecanicaDoJogo;

/**
 * Implementa��o de MecanicaDoJogo que faz com que o jogo acabe ap�s atingir um certo n�mero de rodadas
 * @author Rafael Luckez
 *
 */
public class MecanicaDaQuantidade extends MecanicaDoJogo {

	//Atributo respons�vel por dizer qual � o n�mero de rodadas que o jogo ter�
	private int duracaoJogo;
	
	public MecanicaDaQuantidade() {
		duracaoJogo = 5;
	}
	
	/**
	 * Verifica se o jogo j� atingiu o n�mero m�ximo de rodadas, se sim, indica que o jogo chegou ao fim
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
	 * Retorna a pontua��o do usu�rio de acordo com seu desempenho
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
		return "Voc� est� jogando a vers�o com quantidade de palavras." + System.lineSeparator() + "Sua pontua��o ser� baseada em seus acertos. " + System.lineSeparator() + 
				"O jogo termina ao chegar numa determinada quantidade de palavras";
	}

	
}
