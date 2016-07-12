package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import source.CamelCase;
import source.PalavraInvalidaException;

public class TesteCamelCase {

	private String[] palavras;
	
	@Before
	public void init(){
		palavras = new String[1];
	}
	
	/**
	 * Verifica se a palavra é nula, nesse caso espera-se o lançamento da exceção PalavraInvalidaExcpetion
	 * @throws PalavraInvalidaException
	 */
	@Test(expected = PalavraInvalidaException.class)
	public void stringNullTest() throws PalavraInvalidaException{
		palavras = CamelCase.converterCamelCase(null);
	}
	
	/**
	 * Verifica se a palavra é vazia, nesse caso espera-se o lançamento da exceção PalavraInvalidaExcpetion
	 * @throws PalavraInvalidaException
	 */
	@Test(expected = PalavraInvalidaException.class)
	public void stringVaziaTest() throws PalavraInvalidaException{
		palavras = CamelCase.converterCamelCase("");
	}

	/**
	 * Testa se a String possui caracteres especiais
	 * @throws PalavraInvalidaException
	 */
	@Test(expected = PalavraInvalidaException.class)
	public void caracteresEspeciaisTest() throws PalavraInvalidaException{
		palavras = CamelCase.converterCamelCase("ab#C");
	}
	
	/**
	 * Testa palavra inicia com número
	 * @throws PalavraInvalidaException
	 */
	@Test(expected = PalavraInvalidaException.class)
	public void startWithNumberTest() throws PalavraInvalidaException{
		palavras = CamelCase.converterCamelCase("1aBCa");
	}
	
	/**
	 * Testa se a entrada é retornada em lower case para entradas simples
	 * @throws PalavraInvalidaException
	 */
	@Test
	public void toLowerCaseTest() throws PalavraInvalidaException{
		palavras = CamelCase.converterCamelCase("Nome");
		for(String lowerCase : palavras){
			for(char c : lowerCase.toCharArray()){
				if(Character.isUpperCase(c)){
					fail("lower case fail");
				}
			}
		}
	}
	
	/**
	 * Testa a entrada de várias palavras
	 * @throws PalavraInvalidaException
	 */
	@Test
	public void multipleWordsTest() throws PalavraInvalidaException{
		palavras = CamelCase.converterCamelCase("RafaelTestePapel");
		assertEquals(3, palavras.length);
		assertEquals("rafael", palavras[0]);
		assertEquals("teste", palavras[1]);
		assertEquals("papel", palavras[2]);
	}

	/**
	 * Testa a entrada de palavras com números
	 * @throws PalavraInvalidaException
	 */
	@Test
	public void wordsWithNumberTest() throws PalavraInvalidaException{
		palavras = CamelCase.converterCamelCase("recupera10Primeiros20");
		assertEquals(4, palavras.length);
		assertEquals("recupera", palavras[0]);
		assertEquals("10", palavras[1]);
		assertEquals("primeiros", palavras[2]);
		assertEquals("20", palavras[3]);
	}
	
	@Test
	public void wordsWithAllUperCase() throws PalavraInvalidaException{
		palavras = CamelCase.converterCamelCase("numeroCPFContribuinte");
		assertEquals(3, palavras.length);
		assertEquals("numero", palavras[0]);
		assertEquals("CPF", palavras[1]);
		assertEquals("contribuinte", palavras[2]);
		palavras = CamelCase.converterCamelCase("CPFContribuinteNumero");
		assertEquals(3, palavras.length);
		assertEquals("CPF", palavras[0]);
		assertEquals("contribuinte", palavras[1]);
		assertEquals("numero", palavras[2]);
	}
}
