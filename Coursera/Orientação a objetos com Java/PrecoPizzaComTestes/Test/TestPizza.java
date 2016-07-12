import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestPizza {

	/**
	 * Método executado antes de cada testes para zerar o HashMap de ingrendientes da classe Pizza
	 */
	@Before
	public void zeraIngrendients(){
		Pizza.zeraIngredientes();
	}
	
	/**
	 * Testa o valor de pizza com até 2 ingredientes
	 */
	@Test
	public void testValorPizzaAteDoisIngredientes() {
		Pizza pizza = new Pizza();
		pizza.adicionaIngrediente("Cebola");
		pizza.adicionaIngrediente("Queijo");
		assertEquals(15, pizza.getPreco().intValue());
	}
	
	/**
	 * Testa o valor da pizza com até 5 ingredientes
	 */
	@Test
	public void testValorPizzaAteCincoIngredientes() {
		Pizza pizza = new Pizza();
		pizza.adicionaIngrediente("Cebola");
		pizza.adicionaIngrediente("Queijo");
		pizza.adicionaIngrediente("Picles");
		pizza.adicionaIngrediente("Maionese");
		pizza.adicionaIngrediente("Bacon");
		assertEquals(20, pizza.getPreco().intValue());
	}
	
	/**
	 * Testa o valor da pizza com mais de 5 ingredientes
	 */
	@Test
	public void testValorPizzaMaisQueCincoIngredientes() {
		Pizza pizza = new Pizza();
		pizza.adicionaIngrediente("Cebola");
		pizza.adicionaIngrediente("Queijo");
		pizza.adicionaIngrediente("Picles");
		pizza.adicionaIngrediente("Maionese");
		pizza.adicionaIngrediente("Bacon");
		pizza.adicionaIngrediente("Chocolate");
		pizza.adicionaIngrediente("Xirimóia");
		assertEquals(23, pizza.getPreco().intValue());
	}

	/**
	 * Testa se os valores de ingredientes do HashMap estão sendo adicionados corretamente
	 */
	@Test
	public void testRegistroIngredientes(){
		Pizza pizza1 = new Pizza();
		pizza1.adicionaIngrediente("Cebola");
		pizza1.adicionaIngrediente("Queijo");
		
		Pizza pizza2 = new Pizza();
		pizza2.adicionaIngrediente("Cebola");
		pizza2.adicionaIngrediente("Queijo");
		pizza2.adicionaIngrediente("Picles");
		pizza2.adicionaIngrediente("Maionese");
		pizza2.adicionaIngrediente("Bacon");
		
		assertEquals(2, Pizza.ingredientes.get("Cebola").intValue());
		assertEquals(2, Pizza.ingredientes.get("Queijo").intValue());
		assertEquals(1, Pizza.ingredientes.get("Picles").intValue());
		assertEquals(1, Pizza.ingredientes.get("Maionese").intValue());
		assertEquals(1, Pizza.ingredientes.get("Bacon").intValue());
	}
	
}
