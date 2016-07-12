package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exception.PontoInexistentExcpetion;
import exception.QuantidadeDePontosInvalidaExcpetion;
import exception.UsuarioInexistenteExcpetion;
import exception.UsuarioSemPontosException;
import source.Armazenamento;
import source.Curtida;
import source.Estrela;
import source.Moeda;
import source.Ponto;
import source.Usuario;

public class ArmazenamentoTest {

	private Armazenamento armazenamento;
	
	@Before
	public void init() throws IOException{
		armazenamento = new Armazenamento();
		armazenamento.createControlFile();
	}
	
	@Test
	public void testCriaArquivoDeArmazenamento() throws IOException{
		File f = armazenamento.createControlFile();
		assertTrue(f.exists());
	}
	
	@Test
	public void testLeituraDoArquivoDeArmazenamentoVazio() throws IOException{
		List<String> linesOfFile = armazenamento.readControlFile();
		assertNotNull(linesOfFile);
		assertEquals(0, linesOfFile.size());
	}
	
	@Test
	public void testEscritaNoArquivoDeArmazenamento() throws IOException{
		String content1 = "String de teste 1";
		String content2 = "String de teste 2";
		armazenamento.addContentOnFile(content1);
		armazenamento.addContentOnFile(content2);
		List<String> linesOfFile = armazenamento.readControlFile();
		assertNotNull(linesOfFile);
		assertEquals(2, linesOfFile.size());
		assertEquals(content1, linesOfFile.get(0));
		assertEquals(content2, linesOfFile.get(1));
	}
	
	@Test
	public void testArmazenaUsuario() throws IOException{
		String nomeDoUsuario = "Guerra";
		Usuario usuario = new Usuario(nomeDoUsuario);
		armazenamento.storeUser(usuario);
		List<String> lines = armazenamento.readControlFile();
		assertEquals(1, lines.size());
		assertEquals(nomeDoUsuario, lines.get(0));
	}
	
	@Test(expected=QuantidadeDePontosInvalidaExcpetion.class)
	public void testAdicionaPontoComQuantidadeZero() throws IOException, QuantidadeDePontosInvalidaExcpetion{
		Usuario usuario = new Usuario("Guerra");
		Ponto pontoEstrela = new Estrela();
		armazenamento.addPointsToUser(usuario, pontoEstrela, 0);
	}
	
	@Test
	public void testAdicionaPontoAoUsuarioUmaVez() throws IOException, QuantidadeDePontosInvalidaExcpetion{
		Usuario usuario = new Usuario("Guerra");
		Ponto pontoEstrela = new Estrela();
		int quantidade = 10;
		armazenamento.addPointsToUser(usuario, pontoEstrela, quantidade);
		List<String> lines = armazenamento.readControlFile();
		assertEquals(1, lines.size());
		assertEquals("Guerra;" + pontoEstrela.getTipo() + "=" + quantidade, lines.get(0));
	}
	
	@Test
	public void testAdicionaPontoDoMesmoTipoAoUsuarioMaisDeUmaVez() throws IOException, QuantidadeDePontosInvalidaExcpetion{
		Usuario usuario = new Usuario("Guerra");
		Ponto pontoEstrela = new Estrela();
		int quantidade = 10;
		armazenamento.addPointsToUser(usuario, pontoEstrela, quantidade);
		quantidade = 5;
		armazenamento.addPointsToUser(usuario, pontoEstrela, quantidade);
		quantidade = 25;
		armazenamento.addPointsToUser(usuario, pontoEstrela, quantidade);
		List<String> lines = armazenamento.readControlFile();
		assertEquals(1, lines.size());
		assertEquals("Guerra;" + pontoEstrela.getTipo() + "=" + 40, lines.get(0));
	}
	
	@Test
	public void testAdicionaPontosDiferentesAoUsuario() throws IOException, QuantidadeDePontosInvalidaExcpetion{
		Usuario usuario = new Usuario("Guerra");
		Ponto pontoEstrela = new Estrela();
		int quantidade = 10;
		armazenamento.addPointsToUser(usuario, pontoEstrela, quantidade);
		Ponto pontoMoeda = new Moeda();
		quantidade = 5;
		armazenamento.addPointsToUser(usuario, pontoMoeda, quantidade);
		Ponto pontoCurtida = new Curtida();
		quantidade = 25;
		armazenamento.addPointsToUser(usuario, pontoCurtida, quantidade);
		List<String> lines = armazenamento.readControlFile();
		assertEquals(1, lines.size());
		assertTrue(assertContains(usuario.getName(), lines.get(0)));
		assertTrue(assertContains(pontoEstrela.getTipo() + "=10", lines.get(0)));
		assertTrue(assertContains(pontoMoeda.getTipo() + "=5", lines.get(0)));
		assertTrue(assertContains(pontoCurtida.getTipo() + "=25", lines.get(0)));
	}
	
	public boolean assertContains(String valueToCheck, String stringToCheck){
		if(stringToCheck.contains(valueToCheck))
			return true;
		return false;
	}
	
	@Test
	public void testAdicionaVariosUsuariosComMesmoPonto() throws IOException, QuantidadeDePontosInvalidaExcpetion{
		Usuario usuario1 = new Usuario("Guerra");
		Usuario usuario2 = new Usuario("Rafael");
		Usuario usuario3 = new Usuario("Chico");
		Ponto pontoEstrela = new Estrela();
		int quantidade = 10;
		armazenamento.addPointsToUser(usuario1, pontoEstrela, quantidade);
		quantidade = 5;
		armazenamento.addPointsToUser(usuario2, pontoEstrela, quantidade);
		quantidade = 25;
		armazenamento.addPointsToUser(usuario3, pontoEstrela, quantidade);
		List<String> lines = armazenamento.readControlFile();
		assertEquals(3, lines.size());
		assertEquals(usuario1.getName() + ";" + pontoEstrela.getTipo() + "=10", lines.get(0));
		assertEquals(usuario2.getName() + ";" + pontoEstrela.getTipo() + "=5", lines.get(1));
		assertEquals(usuario3.getName() + ";" + pontoEstrela.getTipo() + "=25", lines.get(2));
	}
	
	@Test
	public void testAdicionaVariosUsuariosComPontosDiferentes() throws IOException, QuantidadeDePontosInvalidaExcpetion{
		Usuario usuario1 = new Usuario("Guerra");
		Usuario usuario2 = new Usuario("Rafael");
		Usuario usuario3 = new Usuario("Chico");
		Ponto pontoEstrela = new Estrela();
		Ponto pontoMoeda = new Moeda();
		Ponto pontoCurtida = new Curtida();
		//Usuario 1
		armazenamento.addPointsToUser(usuario1, pontoEstrela, 10);
		armazenamento.addPointsToUser(usuario1, pontoMoeda, 5);
		//Usuario 2		
		armazenamento.addPointsToUser(usuario2, pontoCurtida, 15);
		armazenamento.addPointsToUser(usuario2, pontoCurtida, 5);
		armazenamento.addPointsToUser(usuario2, pontoEstrela, 5);
		//Usuario 3
		armazenamento.addPointsToUser(usuario3, pontoEstrela, 15);
		armazenamento.addPointsToUser(usuario3, pontoCurtida, 10);
		armazenamento.addPointsToUser(usuario3, pontoMoeda, 10);
		
		List<String> lines = armazenamento.readControlFile();
		
		assertEquals(3, lines.size());
		//Usuario 1
		assertTrue(assertContains(usuario1.getName(), lines.get(0)));
		assertTrue(assertContains(pontoEstrela.getTipo() + "=10", lines.get(0)));
		assertTrue(assertContains(pontoMoeda.getTipo() + "=5", lines.get(0)));
		//Verifica se não existe ponto curtida para o usuário
		assertTrue(!assertContains(pontoCurtida.getTipo(), lines.get(0)));
		
		//Usuario 2
		assertTrue(assertContains(usuario2.getName(), lines.get(1)));
		assertTrue(assertContains(pontoCurtida.getTipo() + "=20", lines.get(1)));
		assertTrue(assertContains(pontoEstrela.getTipo() + "=5", lines.get(1)));
		//Verifica se não existe ponto moeda para o usuário
		assertTrue(!assertContains(pontoMoeda.getTipo(), lines.get(1)));
		
		//Usuario 3
		assertTrue(assertContains(usuario3.getName(), lines.get(2)));
		assertTrue(assertContains(pontoCurtida.getTipo() + "=10", lines.get(2)));
		assertTrue(assertContains(pontoEstrela.getTipo() + "=15", lines.get(2)));
		assertTrue(assertContains(pontoMoeda.getTipo() + "=10", lines.get(2)));
	}
	
	@Test
	public void testRecuperaPontosDeUmTipoDeUmUsuario() throws IOException, PontoInexistentExcpetion, QuantidadeDePontosInvalidaExcpetion{
		Usuario usuario = new Usuario("Guerra");
		Ponto pontoEstrela = new Estrela();
		Ponto pontoMoeda = new Moeda();
		Ponto pontoCurtida = new Curtida();
		armazenamento.addPointsToUser(usuario, pontoEstrela, 10);
		armazenamento.addPointsToUser(usuario, pontoMoeda, 5);
		armazenamento.addPointsToUser(usuario, pontoCurtida, 20);
		assertEquals(10, armazenamento.getUserPoints(usuario, pontoEstrela));
		assertEquals(5, armazenamento.getUserPoints(usuario, pontoMoeda));
		assertEquals(20, armazenamento.getUserPoints(usuario, pontoCurtida));
	}
	
	@Test(expected=PontoInexistentExcpetion.class)
	public void testRecuperaPontosInexistenteDeUmUsuario() throws IOException, PontoInexistentExcpetion{
		Usuario usuario = new Usuario("Guerra");
		Ponto pontoEstrela = new Estrela();
		assertEquals(10, armazenamento.getUserPoints(usuario, pontoEstrela));
	}

	@Test
	public void testRecuperaUsuariosComUmTipoDePonto() throws IOException, QuantidadeDePontosInvalidaExcpetion{
		Usuario usuario1 = new Usuario("Guerra");
		Usuario usuario2 = new Usuario("Rafael");
		Usuario usuario3 = new Usuario("Chico");
		Ponto pontoEstrela = new Estrela();
		Ponto pontoMoeda = new Moeda();
		Ponto pontoCurtida = new Curtida();
		//Usuario 1
		armazenamento.addPointsToUser(usuario1, pontoEstrela, 10);
		armazenamento.addPointsToUser(usuario1, pontoMoeda, 5);
		//Usuario 2		
		armazenamento.addPointsToUser(usuario2, pontoEstrela, 5);
		//Usuario 3
		armazenamento.addPointsToUser(usuario3, pontoEstrela, 15);
		armazenamento.addPointsToUser(usuario3, pontoCurtida, 10);
		armazenamento.addPointsToUser(usuario3, pontoMoeda, 10);
		
		List<Usuario> usersWithEstrela = armazenamento.getUsersWithThatPoint(pontoEstrela);
		List<Usuario> usersWithMoeda = armazenamento.getUsersWithThatPoint(pontoMoeda);
		List<Usuario> usersWithCurtida = armazenamento.getUsersWithThatPoint(pontoCurtida);
		
		assertEquals(3, usersWithEstrela.size());
		assertTrue(usersWithEstrela.contains(usuario1));
		assertTrue(usersWithEstrela.contains(usuario2));
		assertTrue(usersWithEstrela.contains(usuario3));
		
		assertEquals(2, usersWithMoeda.size());
		assertTrue(usersWithMoeda.contains(usuario1));
		assertTrue(usersWithMoeda.contains(usuario3));
		
		assertEquals(1, usersWithCurtida.size());
		assertTrue(usersWithCurtida.contains(usuario3));
	}
	
	@Test
	public void testRecuperaTiposDePontoDeUmUsuario() throws IOException, UsuarioInexistenteExcpetion, UsuarioSemPontosException, QuantidadeDePontosInvalidaExcpetion{
		Usuario usuario = new Usuario("Guerra");
		Ponto pontoEstrela = new Estrela();
		Ponto pontoMoeda = new Moeda();
		Ponto pontoCurtida = new Curtida();
		armazenamento.addPointsToUser(usuario, pontoEstrela, 10);
		armazenamento.addPointsToUser(usuario, pontoMoeda, 5);
		armazenamento.addPointsToUser(usuario, pontoCurtida, 20);
		List<String> allPointsTypeFromUser = armazenamento.getAllPointsTypeFromUser(usuario);
		assertEquals(3, allPointsTypeFromUser.size());
		assertTrue(allPointsTypeFromUser.contains(pontoEstrela.getTipo()));
		assertTrue(allPointsTypeFromUser.contains(pontoMoeda.getTipo()));
		assertTrue(allPointsTypeFromUser.contains(pontoCurtida.getTipo()));
	}
	
	@Test(expected=UsuarioInexistenteExcpetion.class)
	public void testRecuperaTiposDePontoDeUmUsuarioQueNãoExiste() throws IOException, UsuarioInexistenteExcpetion, UsuarioSemPontosException, QuantidadeDePontosInvalidaExcpetion{
		Usuario usuario = new Usuario("Guerra");
		Ponto pontoEstrela = new Estrela();
		armazenamento.addPointsToUser(usuario, pontoEstrela, 10);
		Usuario usuarioInexistente = new Usuario("Inexistente");
		armazenamento.getAllPointsTypeFromUser(usuarioInexistente);
	}
	
	@Test(expected=UsuarioSemPontosException.class)
	public void testRecuperaTiposDePontoDeUmUsuarioQueNãoPossuiPontos() throws IOException, UsuarioInexistenteExcpetion, UsuarioSemPontosException{
		Usuario usuario = new Usuario("Guerra");
		armazenamento.storeUser(usuario);
		armazenamento.getAllPointsTypeFromUser(usuario);
	}
}
