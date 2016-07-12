package test;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import exception.QuantidadeDePontosInvalidaExcpetion;

import static org.junit.Assert.*;
import source.Armazenamento;
import source.Estrela;
import source.Placar;
import source.Ponto;
import source.Usuario;

/**
 * Classe de testes para a classe Placar utilizando um Mock de armazenamento
 * @author Rafael
 *
 */
public class PlacarTest {

	private Placar placar;
	
	@Before
	public void init(){
		try {
			Armazenamento mockArmazenamento = new ArmazenamentoMock();
			placar = new Placar(mockArmazenamento);
		} catch (IOException |QuantidadeDePontosInvalidaExcpetion e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAdicionarPontosAoUsuario() throws IOException, QuantidadeDePontosInvalidaExcpetion {
		Usuario usuario = new Usuario("Guerra");
		Ponto pontoEstrela = new Estrela();
		assertTrue(placar.addPointsToUser(usuario, pontoEstrela, 10));
	}
	
	@Test
	public void testRecuperaTodosPontosDeUmUsuario(){
		Usuario usuario = new Usuario("Guerra");
		String pontosDoGuerra = placar.getAllPointsFromUser(usuario);
		String expected = "O usuário " + usuario.getName() + " possui os seguintes pontos: 5 Moeda - 10 Estrela.";
		assertEquals(expected, pontosDoGuerra);
	}
	
	@Test
	public void testRankingDeUsuariosPorPonto(){
		String result = placar.getUserRankingByPointType(new Estrela());
		String expected = "O ranking de usuários baseado no ponto Estrela é: Chico - 15 Pontos. Guerra - 10 Pontos. Rafael - 5 Pontos.";
		assertEquals(expected, result);
	}

}
