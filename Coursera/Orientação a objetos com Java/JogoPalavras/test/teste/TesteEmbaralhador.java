package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import impl.EmbaralhadorAleatorio;
import impl.EmbaralhadorInverter;
import impl.EmbaralhadorSeparaImparEPar;
import interfaces.Embaralhador;

import org.junit.Test;

import exception.PalavraInvalidaException;

public class TesteEmbaralhador {

	private Embaralhador embaralhador;
	
	/**
	 * Teste que verifia o correto funcionamento de um embaralhador que inverte a palavra
	 */
	@Test
	public void testEmbaralhadorInverter() throws PalavraInvalidaException {
		embaralhador = new EmbaralhadorInverter();
		assertEquals("uagnim", embaralhador.embaralhar("mingau"));
	}
	
	/**
	 * Teste que verifia o lançamento de exceção em caso de embaralhamento de uma palavra que não é possível
	 * ser embaralhada pelo embaralhador inverter
	 */
	@Test(expected=PalavraInvalidaException.class)
	public void testEmbaralhadorInverterPalavraInvalida() throws PalavraInvalidaException{
		embaralhador = new EmbaralhadorInverter();
		assertEquals("1111", embaralhador.embaralhar("1111"));
	}
	
	
	/**
	 * Teste que verifia o lançamento de exceção em caso de embaralhamento de uma palavra que não é possível
	 * ser embaralhada pelo embaralhador aleatorio
	 */
	@Test(expected=PalavraInvalidaException.class)
	public void testEmbaralhadorAleatorioPalavraInvalida() throws PalavraInvalidaException{
		embaralhador = new EmbaralhadorAleatorio();
		assertEquals("1111", embaralhador.embaralhar("1111"));
	}
	/**
	 * Teste que verifica o correto funcionamento do embaralhador aleatório
	 */
	@Test
	public void testEmbaralhadorAleatorio() throws PalavraInvalidaException{
		//palavra que será embaralhada
		String palavraparaEmbaralhar = "mingau";
		//Caracteres da palavra que foi embaralhada
		char[] caracteresPreEmbaralhamento = palavraparaEmbaralhar.toCharArray();
		//Instancia o embaralhador
		embaralhador = new EmbaralhadorAleatorio();
		//palavra resultando do embaralhamento
		String palavraEmbaralhada = embaralhador.embaralhar(palavraparaEmbaralhar);
		
		//Verifica se o embaralhamento não retornou igual
		assertNotEquals(palavraparaEmbaralhar, palavraEmbaralhada);
		//Verifica se o embaralhamento retornou uma palavra do mesmo tamanho
		assertEquals(palavraparaEmbaralhar.length(), palavraEmbaralhada.length());
		//Lógica utilizada para verificar se todos caracteres que existiam na palavra original aidna existem na palavra embaralhada
		for (int i = 0; i < caracteresPreEmbaralhamento.length; i++) {
			if(!palavraEmbaralhada.contains(String.valueOf(caracteresPreEmbaralhamento[i]))){
				fail();
			}
		}
	}

	
	/**
	 * Teste que verifia o correto funcionamento de um embaralhador que inverte a palavra
	 */
	@Test
	public void testEmbaralhadorSeparaImparEPar() throws PalavraInvalidaException {
		embaralhador = new EmbaralhadorSeparaImparEPar();
		assertEquals("Mnaigu", embaralhador.embaralhar("Mingau"));
	}
}
