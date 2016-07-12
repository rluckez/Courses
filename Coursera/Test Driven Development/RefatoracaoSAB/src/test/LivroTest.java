package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import source.Livro;
import source.Usuario;

public class LivroTest {
	@Before
	public void SetUp() {
		livro = new Livro("Java Design Patterns", "Pankaj Kumar");
	}

	@Test
	public void whenSitua��oInicialEntaoLivroDisponivelParaEmprestimo() {
		// T1: Testa condi��es de in�cio!
		// Assumo que Biblioteca n�o deixa criar livro com
		// titulo e/ou autor com nomes vazios ou nulos!
		assertEquals(null, livro.getUsuario());
		assertEquals("Java Design Patterns", livro.getTitulo());
		assertEquals("Pankaj Kumar", livro.getAutor());
	}

	@Test
	public void whenAnexaUsuarioNaoNuloAoLivroEntaoLivroFicaIndisponivelParaEmprestimo() {
		// T2: Anexa usuario n�o nulo
		Usuario usuario1 = new Usuario("Jos�");
		livro.anexaUsuarioAoLivro(usuario1);
		assertEquals(usuario1, livro.getUsuario());
	}

	@Test
	public void whenAnexaUsuarioNuloAoLivroEntaoLivroFicaDisponivelParaEmprestimo() {
		// T3: Anexa usuario nulo
		Usuario usuario1 = null;
		livro.anexaUsuarioAoLivro(usuario1);
		assertEquals(usuario1, livro.getUsuario());
	}

	@Test
	public void whenDesanexaUsuarioNaoNuloDoLivroEntaoLivroFicaDisponivelParaEmprestimo() {
		// T4: Desanexa usuario n�o nulo
		Usuario usuario1 = new Usuario("Jos�");
		livro.anexaUsuarioAoLivro(usuario1);
		livro.desanexaUsuarioDoLivro();
		assertEquals(null, livro.getUsuario());
	}

	@Test
	public void whenDesanexaUsuarioNuloDoLivroEntaoLivroContinuaDisponivelParaEmprestimo() {
		// T5: Desanexa usuario nulo
		Usuario usuario1 = null;
		livro.anexaUsuarioAoLivro(usuario1);
		livro.desanexaUsuarioDoLivro();
		assertEquals(null, livro.getUsuario());
	}

	private Livro livro;
}
