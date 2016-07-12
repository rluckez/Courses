package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import exception.QuantidadeDePontosInvalidaExcpetion;
import source.Armazenamento;
import source.Curtida;
import source.Estrela;
import source.Moeda;
import source.Placar;
import source.Ponto;
import source.Usuario;

public class IntegracaoTest {

	private Placar placar;
	private Usuario guerra = new Usuario("Guerra");
	private Usuario rafael = new Usuario("Rafael");
	private Usuario chico = new Usuario("Chico");
	private Ponto estrela = new Estrela();
	private Ponto moeda = new Moeda();
	private Ponto curtida = new Curtida();
	
	@Before
	public void init(){
		Armazenamento armazenamento = new Armazenamento();
		placar = new Placar(armazenamento);
	}
	
	@Test
	public void testAdicionarPontosAoUsuario() throws IOException, QuantidadeDePontosInvalidaExcpetion {
		assertTrue(placar.addPointsToUser(guerra, estrela, 10));
	}
	
	@Test
	public void testRecuperaTodosPontosDeUmUsuario(){
		placar.addPointsToUser(guerra, estrela, 10);
		placar.addPointsToUser(guerra, moeda, 5);
		String pontosDoGuerra = placar.getAllPointsFromUser(guerra);
		String expected = "O usuário " + guerra.getName() + " possui os seguintes pontos: 5 Moeda - 10 Estrela.";
		assertEquals(expected, pontosDoGuerra);
	}
	
	@Test
	public void testRankingDeUsuariosPorPonto(){
		placar.addPointsToUser(guerra, estrela, 15);
		placar.addPointsToUser(rafael, estrela, 10);
		placar.addPointsToUser(chico, estrela, 5);
		String result = placar.getUserRankingByPointType(new Estrela());
		String expected = "O ranking de usuários baseado no ponto Estrela é: Guerra - 15 Pontos. Rafael - 10 Pontos. Chico - 5 Pontos.";
		assertEquals(expected, result);
	}
	
	@Test
	public void testIntegracaoPlacarEArmazenamento() throws IOException, QuantidadeDePontosInvalidaExcpetion {

		assertTrue(placar.addPointsToUser(guerra, estrela, 10));
		assertTrue(placar.addPointsToUser(guerra, estrela, 20));
		assertTrue(placar.addPointsToUser(guerra, curtida, 5));
		assertTrue(placar.addPointsToUser(guerra, moeda, 15));
		
		assertTrue(placar.addPointsToUser(rafael, estrela, 35));
		assertTrue(placar.addPointsToUser(rafael, moeda, 10));
		assertTrue(placar.addPointsToUser(rafael, curtida, 25));
		
		assertTrue(placar.addPointsToUser(chico, estrela, 10));
		assertTrue(placar.addPointsToUser(chico, curtida, 20));
		
		String guerraPoints = placar.getAllPointsFromUser(guerra);
		assertContains(guerra.getName(), guerraPoints);
		assertContains("30 Estrela", guerraPoints);
		assertContains("5 Curtida", guerraPoints);
		assertContains("15 Moeda", guerraPoints);
		
		String rafaelPoints = placar.getAllPointsFromUser(guerra);
		assertContains(rafael.getName(), rafaelPoints);
		assertContains("35 Estrela", rafaelPoints);
		assertContains("25 Curtida", rafaelPoints);
		assertContains("10 Moeda", rafaelPoints);
		
		String chicoPoints = placar.getAllPointsFromUser(guerra);
		assertContains(chico.getName(), chicoPoints);
		assertContains("10 Estrela", chicoPoints);
		assertContains("20 Curtida", chicoPoints);
		
		
		String expected = "O ranking de usuários baseado no ponto Estrela é: Rafael - 35 Pontos. Guerra - 30 Pontos. Chico - 10 Pontos.";
		assertEquals(expected, placar.getUserRankingByPointType(estrela));
		
		expected = "O ranking de usuários baseado no ponto Moeda é: Guerra - 15 Pontos. Rafael - 10 Pontos.";
		assertEquals(expected, placar.getUserRankingByPointType(moeda));
		
		expected = "O ranking de usuários baseado no ponto Curtida é: Rafael - 25 Pontos. Chico - 20 Pontos. Guerra - 5 Pontos.";
		assertEquals(expected, placar.getUserRankingByPointType(curtida));
		
	}
	
	public boolean assertContains(String valueToCheck, String stringToCheck){
		if(stringToCheck.contains(valueToCheck))
			return true;
		return false;
	}

}
