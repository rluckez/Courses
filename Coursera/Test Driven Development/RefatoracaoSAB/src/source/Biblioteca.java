package source;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import exception.AdicionarLivroInexistenteException;
import exception.BuscaUsuarioComNomeNuloException;
import exception.BuscaUsuarioComNomeVazioException;
import exception.DevolveLivroDisponivelParaEmprestimoException;
import exception.DevolveLivroNuloParaEmprestimoException;
import exception.LivroIndisponivelParaEmprestimoException;
import exception.LivroOuUsuarioNulosException;
import exception.TituloOuAutorNuloException;
import exception.TituloOuAutorVazioException;
import exception.UsuarioComNomeVazioException;
import exception.UsuarioInexistenteException;
import exception.UsuarioJaRegistradoException;

public class Biblioteca {

	public Biblioteca(String nome) {
		_nome = nome;
		_repositorioLivros = new TreeSet<Livro>();
		_usuarios = new HashSet<Usuario>();
	}

	public void adicionaLivroCatalogo(Livro livro)
			throws AdicionarLivroInexistenteException {
		if (livro != null) {
			livro.setNrCatalogo(this.getNrUnico());
			_repositorioLivros.add(livro);
		} else
			throw new AdicionarLivroInexistenteException(
					"--->Não pode adicionar livro inexistente!");
	}

	public void registraUsuario(String nome) throws UsuarioJaRegistradoException, UsuarioComNomeVazioException,	UsuarioInexistenteException {
		
		if (nome == null) throw new UsuarioInexistenteException("--->Não pode registrar usuario inexistente!");
		if (nome.isEmpty()) throw new UsuarioComNomeVazioException("--->Não pode registrar usuario com nome vazio!");
		Usuario usuario = new Usuario(nome);
		if (_usuarios.contains(usuario)) throw new UsuarioJaRegistradoException("--->Já existe usuário com o nome \"" + nome + "\"! Use outro nome!"); 
		_usuarios.add(usuario);
	}

	public void emprestaLivro(Livro livro, Usuario usuario)
			throws LivroIndisponivelParaEmprestimoException,
			LivroOuUsuarioNulosException {
		if ((livro == null) && (usuario == null))
			throw new LivroOuUsuarioNulosException(
					"--->Livro e Usuário inexistentes!");
		if (livro != null) {
			if (usuario != null) {
				if (livro.getUsuario() == null) {
					usuario.anexaLivroAoUsuario(livro);
					livro.anexaUsuarioAoLivro(usuario);
				} else
					throw new LivroIndisponivelParaEmprestimoException(
							"--->Livro " + livro
									+ " indisponível para empréstimo!");
			} else
				throw new LivroOuUsuarioNulosException(
						"--->Usuário inexistente!");
		} else
			throw new LivroOuUsuarioNulosException(
					"--->Não pode emprestar livro inexistente!");
	}

	public void devolveLivro(Livro livro)
			throws DevolveLivroDisponivelParaEmprestimoException,
			DevolveLivroNuloParaEmprestimoException {
		if (livro != null) {
			Usuario usuario = livro.getUsuario();
			if (usuario != null) {
				usuario.desanexaLivroDoUsuario(livro);
				livro.desanexaUsuarioDoLivro();
			} else
				throw new DevolveLivroDisponivelParaEmprestimoException(
						"---> Tentou devolver livro " + livro
								+ " que está disponível para empréstimo!");
		} else
			throw new DevolveLivroNuloParaEmprestimoException(
					"--->Não pode emprestar livro inexistente!");
	}

	public Livro buscaLivroPorNrCatalogo(int nrUnico) {
		// nrUnico <= zero devolve nulo: não encontrou livro algum!
		Livro livroAchado = null;
		Iterator<Livro> iter = _repositorioLivros.iterator();
		while ((iter.hasNext() == true) && (livroAchado == null)) {
			Livro livro = (Livro) iter.next();
			int oNrUnico = livro.getNrCatalogo();
			if (oNrUnico == nrUnico)
				livroAchado = livro;
		}
		return livroAchado;
	}

	public Livro buscaLivroPorTituloAutor(String titulo, String autor)
			throws TituloOuAutorVazioException, TituloOuAutorNuloException {
		Livro livroAchado = null;
		if ((titulo != null) && (autor != null)) {
			if (!titulo.isEmpty() && !autor.isEmpty()) {
				Iterator<Livro> iter = _repositorioLivros.iterator();
				while ((iter.hasNext() == true) && (livroAchado == null)) {
					Livro livro = (Livro) iter.next();
					String oTitulo = livro.getTitulo();
					String oAutor = livro.getAutor();
					if ((oTitulo.equals(titulo)) && (oAutor.equals(autor))) {
						livroAchado = livro;
					}
				}
			} else
				throw new TituloOuAutorVazioException(
						"--->Nome do titulo e/ou do autor é(são) vazio(s)<<<");
		} else
			throw new TituloOuAutorNuloException(
					"--->Nome do titulo e/ou do autor é(são) nulo(s)<<<");
		return livroAchado;
	}

	public Usuario buscaUsuarioPorNome(String nome)
			throws BuscaUsuarioComNomeVazioException,
			BuscaUsuarioComNomeNuloException {
		Usuario usuarioAchado = null;
		if ((nome != null)) {
			if (!nome.isEmpty()) {
				Iterator<Usuario> iter = _usuarios.iterator();
				while ((iter.hasNext() == true) && (usuarioAchado == null)) {
					Usuario usuario = (Usuario) iter.next();
					String oNome = usuario.getNome();
					if (oNome == nome) {
						usuarioAchado = usuario;
					}
				}
			} else
				throw new BuscaUsuarioComNomeVazioException(
						"--->Nome do usuário é vazio<<<");
		} else
			throw new BuscaUsuarioComNomeNuloException(
					"--->Nome do usuário é nulo<<<");
		return usuarioAchado;
	}

	public void exibeLivrosDisponíveis() {
		System.out.println("Biblioteca: " + _nome);
		System.out.println(">>>Livros Disponíveis para Empréstimo<<<");
		if (_repositorioLivros.size() != 0) {
			Iterator<Livro> iter = _repositorioLivros.iterator();
			while (iter.hasNext() == true) {
				Livro livro = (Livro) iter.next();
				if (livro.getUsuario() == null) {
					livro.exibe();
				}
			}
		} else
			System.out.println("---> Nenhum livro no repositório");
		System.out.println("<<< Livros Disponíveis >>>");
		System.out.println();
	}

	public void exibeLivrosEmprestados() {
		System.out.println("Biblioteca: " + _nome);
		System.out.println(">>>Livros Emprestados<<<");
		if (_repositorioLivros.size() != 0) {
			Iterator<Livro> iter = _repositorioLivros.iterator();
			while (iter.hasNext() == true) {
				Livro livro = (Livro) iter.next();
				if (livro.getUsuario() != null) {
					System.out.println("\t\t"
							+ "--------------------------------------------");
					livro.exibe();
				}
			}
		} else
			System.out.println("---> Nenhum livro no repositório");
		System.out.println("<<< Livros Emprestados >>>");
		System.out.println();
	}

	public void exibeUsuarios() {
		System.out.println("Biblioteca: " + _nome);
		System.out.println(">>>Usuários da Biblioteca<<<");
		if (_usuarios.size() != 0) {
			Iterator<Usuario> iter = _usuarios.iterator();
			while (iter.hasNext() == true) {
				Usuario usuario = (Usuario) iter.next();
				usuario.exibe();
			}
		} else
			System.out.println("---> Nenhum usuário na Biblioteca");
		System.out.println("<<< Usuários >>>");
		System.out.println();
	}

	private int getNrUnico() {
		// Assumo que cada livro recebe um nrUnico diferente
		return _nrUnico = _nrUnico + 1;
	}

	public int sizeRepositorioLivros() {
		return _repositorioLivros.size();
	}

	public int sizeUsuarios() {
		return _usuarios.size();
	}

	private String _nome;
	private int _nrUnico = 0; // _nrUnico > zero!
	private TreeSet<Livro> _repositorioLivros;
	private HashSet<Usuario> _usuarios;
}
