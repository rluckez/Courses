package gameclass;

import interfaces.MecanicaDoJogo;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

import exception.PalavraInvalidaException;

public class Principal {

	public static void main(String[] args) {
		
		//Obt�m uma inst�ncia de mec�ncia do jogo aleatoriamente
		MecanicaDoJogo mdj = FabricaMecanicaDoJogo.getMecanicaDoJogo();
		System.out.println(mdj.getJogoInfo());
		//Executa uma rodada do jogo
		novaRodada(mdj);

	}
	
	private static void novaRodada(MecanicaDoJogo mdj){
		Scanner scanner = new Scanner(System.in);
		//Loop executado enquanto o jogo n�o acabar
		while (!mdj.acabou()) {
			//Vari�vel que armazenar� a palavra da rodada atual
			String palavraDaVez;
			String palavraEmbaralhada;
			try {
				//obt�m uma palavra aleat�ria
				palavraDaVez = mdj.getPalavra();
				palavraEmbaralhada = mdj.embaralha(palavraDaVez);
				System.out.println("------------------------------------");
				//Pergunta ao jogador qual � a palavra que est� embaralhada
				System.out.println("Responda qual � esta palavra: " + palavraEmbaralhada);
				//Recebea a resposta do jogador
				String resp = scanner.next();
				//Verifica se o jogador acertou a palavra
				if(palavraDaVez.equalsIgnoreCase(resp)){
					System.out.println("Parab�ns, voc� acertou");
					mdj.rodada(true);
				}else{
					System.out.println("Infelizmente voc� errou, a palavra era: " + palavraDaVez);
					mdj.rodada(false);
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Houve problemas ao tentar obter uma palavra do arquivo de palavras");
			} catch (URISyntaxException e) {
				e.printStackTrace();
				System.out.println("Caminho especificado para o arquivo de palavras n�o existe e/ou est� incorreto");
			} catch (PalavraInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Fecha o objeto scanner
		scanner.close();
		//C�digo executado ao fim do jogo para mostrar ao jogador sua pontua��o
		System.out.println("<<<<<<<<<---------------FIM DE JOGO ------------------->>>>>>>>");
		System.out.println("Voc� acertou " + (mdj.getQtdPalavras() - mdj.getErros()) + " palavras de um total de "
				+ mdj.getQtdPalavras() + " palavras");
		System.out.println("Voc� teve " + mdj.getErros() +" erro(s) ");
		System.out.println("Voc� fez " + mdj.getPontos() + " pontos");
	}

}
