package interfaces;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import exception.PalavraInvalidaException;
import gameclass.BancoDePalavra;
import gameclass.FabricaEmbaralhadores;

/**
 * Classe abstrata respons�vel pela mec�nica do jogo
 * @author Rafael Luckezs
 *
 */
public abstract class MecanicaDoJogo {
	//Atributos para armazenar o status do jogo
	protected int pontos = 0, qtdPalavras = 0, acertos = 0, erros = 0;
	//Embaralhador respons�vel por embaralhar a palavra do jogo
	protected Embaralhador embaralhador;
	//M�todo que indica quando jogo chega ao fim
	public abstract boolean acabou();
	//M�todo que obmt�m informa��es sobre as regras do jogo
	public abstract String getJogoInfo();
	//M�todo respons�vel por contabilizar uma rodada do jogo
	public abstract void rodada(boolean acertou);
	
	/**
	 * Obt�m uma plavra do banco de palavras
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
