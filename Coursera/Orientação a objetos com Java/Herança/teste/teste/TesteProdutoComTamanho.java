package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.ProdutoComTamanho;

public class TesteProdutoComTamanho {

private ProdutoComTamanho p1, p2, p3, p4;
	
	@Before
	public void init(){
		p1 = new ProdutoComTamanho("Sapato", 1, 5, 10);
		p2 = new ProdutoComTamanho("Roupa", 2, 15, 1);
		p3 = new ProdutoComTamanho("Sapato", 1, 5, 10);
		p4 = new ProdutoComTamanho("Sapato", 1, 5, 20);
	}
	
	/**
	 * Testa o método equals para objetos iguais e objetos diferentes
	 */
	@Test
	public void testObjetosIguais() {
		assertFalse(p1.equals(p2));
		assertFalse(p1.equals(p2));
		assertTrue(p1.equals(p3));
		assertFalse(p1.equals(p4));
	}
	
	/**
	 * Testa o método HashCode de cada produto
	 */
	@Test
	public void testHashCode() {
		assertEquals(11, p1.hashCode());
		assertEquals(3, p2.hashCode());
		assertEquals(11, p3.hashCode());
		assertEquals(21, p4.hashCode());
	}

}
