package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.CarrinhoDeCompra;
import classes.Produto;
import classes.ProdutoComTamanho;

public class TesteCarrinhoDeCompra {

	private CarrinhoDeCompra cdp;
	private Produto p1, p2;
	private ProdutoComTamanho pct1, pct2;
	
	/**
	 * Inicializa os objetos que serão utilizados nos testes
	 */
	@Before
	public void init(){
		cdp = new CarrinhoDeCompra();
		
		p1 = new Produto("Sapato", 1, 5);
		p2 = new Produto("Roupa", 2, 15);
		
		pct1 = new ProdutoComTamanho("Sapato", 1, 5, 10);
		pct2 = new ProdutoComTamanho("Roupa", 2, 15, 1);
		
	}
	
	/**
	 * Testa a adição de produtos
	 */
	@Test
	public void testAdicionaProduto() {
		cdp.adicionaProduto(p1, 3);
		cdp.adicionaProduto(p2, 2);
		assertEquals(Integer.valueOf(3), cdp.getProdutos().get(p1));
		assertEquals(Integer.valueOf(2), cdp.getProdutos().get(p2));
	}
	
	/**
	 * Testa a adição de produtos com tamanho
	 */
	@Test
	public void testAdicionaProdutoComTamanho() {
		cdp.adicionaProduto(pct1, 3);
		cdp.adicionaProduto(pct2, 2);
		assertEquals(Integer.valueOf(3), cdp.getProdutos().get(pct1));
		assertEquals(Integer.valueOf(2), cdp.getProdutos().get(pct2));
	}
	
	/**
	 * Testa o cálculo do valor total dos produtos inseridos no carrinho
	 */
	@Test
	public void testCalculaTotalDoCarrinho(){
		cdp.adicionaProduto(p1, 2); //10
		cdp.adicionaProduto(p2, 4); //60
		cdp.adicionaProduto(pct1, 1); //5
		cdp.adicionaProduto(pct2, 3); //45
		assertEquals(120, cdp.calculaValorTotal());
	}
	
	/**
	 * Testa o método de remoção de produtos do carrinho
	 */
	@Test
	public void testRemoveProduto(){
		cdp.adicionaProduto(p1, 2);
		//Tenta remover mais do que tem no carrinho
		cdp.removeProduto(p1, 3);
		//A quantidade deve ser a msm, já que a remoção não pôde ser realizada
		assertEquals(Integer.valueOf(2), cdp.getProdutos().get(p1));
		//Remove 1
		cdp.removeProduto(p1, 1);
		//Verifica se foi removido 1
		assertEquals(Integer.valueOf(1), cdp.getProdutos().get(p1));
		//Remove mais 1
		cdp.removeProduto(p1, 1);
		//Verifica se o produto foi removido do carrinho, já que sua quantidade é zero
		assertNull(cdp.getProdutos().get(p1));
	}

}
