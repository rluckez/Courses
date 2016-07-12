package interfaces;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import exception.PalavraInvalidaException;
import gameclass.BancoDePalavra;
import gameclass.FabricaEmbaralhadores;

/**
 * Classe abstrata responsável pela mecânica do jogo
 * @author Rafael Luckezs
 *
 */
public abstract class MecanicaDoJogo {
	//Atributos para armazenar o status do jogo
	protected int pontos = 0, qtdPalavras = 0, acertos = 0, erros = 0;
	//Embaralhador responsável por embaralhar a palavra do jogo
	protected Embaralhador embaralhador;
	//Método que indica quando jogo chega ao fim
	public abstract boolean acabou();
	//Método que obmtém informações sobre as regras do jogo
	public abstract String getJogoInfo();
	//Método responsável por contabilizar uma rodada do jogo
	public abstract void rodada(boolean acertou);
	
	/**
	 * Obtém uma plavra do banco de palavras
	 */
	public String getPalavra() throws FileNotFoundException, URISyntaxException {
		return BancoDePalavra.getPalavra();
	}
	
	public MecanicaDoJogo(){
		this.embaralhador = FabricaEmbaralhadores.getEmbaralhador();
	}

	public String embaralha(String palavra) throws PalavraInvalidaException{
		return this.embaralhador.embaralhar(palavra);
	}
	
	public int getPontos(){
		return pontos;
	}
	
	public int getErros(){
		return erros;
	}
	
	public int getQtdPalavras(){
		return qtdPalavras;
	}
	
	public int getAcertos(){
		return acertos;
	}
	
}
