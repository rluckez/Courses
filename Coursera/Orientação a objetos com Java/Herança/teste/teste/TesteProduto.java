package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.Produto;

public class TesteProduto {

	private Produto p1, p2, p3;
	
	@Before
	public void init(){
		p1 = new Produto("Sapato", 1, 5);
		p2 = new Produto("Roupa", 2, 15);
		p3 = new Produto("Sapato", 1, 5);
	}
	
	/**
	 * Testa o método equals, comparando objetos iguais e objetos diferentes
	 */
	@Test
	public void testObjetosIguais() {
		assertFalse(p1.equals(p2));
		assertTrue(p1.equals(p3));
	}
	
	/**
	 * Testa o método HashCode de cada produto
	 */
	@Test
	public void testHashCode() {
		assertEquals(1, p1.hashCode());
		assertEquals(2, p2.hashCode());
		assertEquals(1, p3.hashCode());
	}
	
}
