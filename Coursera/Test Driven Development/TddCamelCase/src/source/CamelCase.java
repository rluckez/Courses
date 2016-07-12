package source;


public class CamelCase {
	
	/**
	 * Realiza o split da String recebida através do uso de expressão regular
	 * @param original String que se deseja dividir
	 * @return Lista com as palavras que foram separadas da String
	 * @throws PalavraInvalidaException
	 */
	public static String[] converterCamelCase(String original) throws PalavraInvalidaException{
		validaPalavra(original);
		/**
		 * A expressão regular foi dividida em 4 partes
		 * (?<=[a-z])(?=[A-Z])      Analisa se a String contém o seguinte formato: nomeComposto
		 * (?<=[A-Z])(?=[A-Z][a-z]) Analisa se a String contém o seguinte formato: contribuinteCPFTeste
		 * (?<=[A-Za-z])(?=\\d)     Analisa se a String contém o seguinte formato: abc95 ou ABC95
		 * (?<=\\d)(?=[A-Z][a-z])   Analisa se a String contém trechos que se iniciam em números seguidos de letras
		 */
		String[] words = original.split("(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])|(?<=[A-Za-z])(?=\\d)|(?<=\\d)(?=[A-Z][a-z])");
		return toLowerCaseIfNecessary(words);
	}

	/**
	 * Faz a validação da String de entrada, verifica se é nula ou vazia, se possui caracteres especiais e se inicia com número
	 * @param word Palavra que será validada
	 * @throws PalavraInvalidaException
	 */
	public static void validaPalavra(String word) throws PalavraInvalidaException{
		if(word == null || word.equalsIgnoreCase("") || specialCharacterCheck(word) || startWithNumber(word)){
			throw new PalavraInvalidaException();
		}
	}
	
	/**
	 * Através do uso de expressão regular verifica se a String contém caracteres especiais
	 * @param word Palavra a ser validada
	 * @return True ou False indicando se a palavra contém caractere especial
	 */
	public static boolean specialCharacterCheck(String word){
		return !word.matches("^[\\p{Alnum}]+$");   
	}
	
	/**
	 * Faz a conversão para mínusculas em palavras que não possui somente caracteres em maíusculo
	 * @param words Lista de palavras que serão analisadas
	 * @return Lista com as devidas string convertidas
	 */
	public static String[] toLowerCaseIfNecessary(String[] words){
		for(int i = 0; i < words.length; i++){
			for(char c: words[i].toCharArray()){
				if(Character.isLowerCase(c)){
					words[i] = words[i].toLowerCase();
					break;
				}
			}
		}
		return words;
	}
	
	/**
	 * Verifica se a String inicia com número
	 * @param word Palavra a ser validada
	 * @return True ou False indicando se a palavra inicia ou não com número
	 */
	public static boolean startWithNumber(String word){
		return Character.isDigit(word.charAt(0));
	}
}
