import static org.junit.Assert.*;

import org.junit.Test;

public class TestCarrinhoDeCompra {

	/**
	 * Testa se o valor do carrinho está sendo somado corretamente
	 */
	@Test
	public void testValorTotalDoCarrinho() {
		
		Pizza pizza1 = new Pizza();
		pizza1.adicionaIngrediente("Cebola");
		pizza1.adicionaIngrediente("Queijo");

		Pizza pizza2 = new Pizza();
		pizza2.adicionaIngrediente("Cebola");
		pizza2.adicionaIngrediente("Queijo");
		pizza2.adicionaIngrediente("Picles");
		pizza2.adicionaIngrediente("Maionese");
		pizza2.adicionaIngrediente("Bacon");

		Pizza pizza3 = new Pizza();
		pizza3.adicionaIngrediente("Cebola");
		pizza3.adicionaIngrediente("Queijo");
		pizza3.adicionaIngrediente("Picles");
		pizza3.adicionaIngrediente("Maionese");
		pizza3.adicionaIngrediente("Bacon");
		pizza3.adicionaIngrediente("Chocolate");
		pizza3.adicionaIngrediente("Xirimóia");
		
		CarrinhoDeCompra cdp = new CarrinhoDeCompra();
		cdp.adicionaPizzaNoCarrinho(pizza1);
		cdp.adicionaPizzaNoCarrinho(pizza2);
		cdp.adicionaPizzaNoCarrinho(pizza3);
		
		assertEquals(58, cdp.getValorTotalDoCarrinho());
	}

	/**
	 * Verifica se o carrinho permite a adição de pizzas sem ingredientes
	 */
	@Test
	public void testPizzaSemIngredientes() {
		Pizza p1 = new Pizza();
		Pizza p2 = new Pizza();
		CarrinhoDeCompra cdp = new CarrinhoDeCompra();
		cdp.adicionaPizzaNoCarrinho(p1);
		cdp.adicionaPizzaNoCarrinho(p2);
		
		assertEquals(0, cdp.getValorTotalDoCarrinho());
		assertEquals(0, cdp.pizzas.size());
	}

}
