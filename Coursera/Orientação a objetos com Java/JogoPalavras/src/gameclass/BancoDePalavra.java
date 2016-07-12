package gameclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe responsável por realizar a leitura de um arquivo com diversas palavras e retornar uma delas aleatoriamente
 * @author Rafael Luckez
 *
 */
public class BancoDePalavra {

	/**
	 * Método estático responsáel por retonar uma palavra aleatoriamente
	 */
	public static String getPalavra() throws URISyntaxException, FileNotFoundException{
		
		Random random = new Random();
		
		URL url = Principal.class.getResource("palavras.txt");
		File file = new File(url.toURI());
	
		Scanner fileIn = new Scanner(file);
		
		List<String> palavras = new ArrayList<String>();
		
		//Insere todas palavras do arquivo numa lista
		while(fileIn.hasNextLine()){
			palavras.add(fileIn.nextLine());
		}
		
		fileIn.close();
		//Retorna aleatoriamente uma palavra da lista de palavras
		return palavras.get(random.nextInt(palavras.size() - 1));
	}
	
}
