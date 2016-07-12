package gameclass;

import interfaces.MecanicaDoJogo;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

import exception.PalavraInvalidaException;

public class Principal {

	public static void main(String[] args) {
		
		//Obtém uma instância de mecância do jogo aleatoriamente
		MecanicaDoJogo mdj = FabricaMecanicaDoJogo.getMecanicaDoJogo();
		System.out.println(mdj.getJogoInfo());
		//Executa uma rodada do jogo
		novaRodada(mdj);

	}
	
	private static void novaRodada(MecanicaDoJogo mdj){
		Scanner scanner = new Scanner(System.in);
		//Loop executado enquanto o jogo não acabar
		while (!mdj.acabou()) {
			//Variável que armazenará a palavra da rodada atual
			String palavraDaVez;
			String palavraEmbaralhada;
			try {
				//obtém uma palavra aleatória
				palavraDaVez = mdj.getPalavra();
				palavraEmbaralhada = mdj.embaralha(palavraDaVez);
				System.out.println("------------------------------------");
				//Pergunta ao jogador qual é a palavra que está embaralhada
				System.out.println("Responda qual é esta palavra: " + palavraEmbaralhada);
				//Recebea a resposta do jogador
				String resp = scanner.next();
				//Verifica se o jogador acertou a palavra
				if(palavraDaVez.equalsIgnoreCase(resp)){
					System.out.println("Parabéns, você acertou");
					mdj.rodada(true);
				}else{
					System.out.println("Infelizmente você errou, a palavra era: " + palavraDaVez);
					mdj.rodada(false);
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Houve problemas ao tentar obter uma palavra do arquivo de palavras");
			} catch (URISyntaxException e) {
				e.printStackTrace();
				System.out.println("Caminho especificado para o arquivo de palavras não existe e/ou está incorreto");
			} catch (PalavraInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Fecha o objeto scanner
		scanner.close();
		//Código executado ao fim do jogo para mostrar ao jogador sua pontuação
		System.out.println("<<<<<<<<<---------------FIM DE JOGO ------------------->>>>>>>>");
		System.out.println("Você acertou " + (mdj.getQtdPalavras() - mdj.getErros()) + " palavras de um total de "
				+ mdj.getQtdPalavras() + " palavras");
		System.out.println("Você teve " + mdj.getErros() +" erro(s) ");
		System.out.println("Você fez " + mdj.getPontos() + " pontos");
	}

}
