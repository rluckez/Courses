import java.util.Map.Entry;

public class Principal {
	
	public static void main(String[] args) {
		
		Pizza p1 = new Pizza();
		p1.adicionaIngrediente("Banana");
		p1.adicionaIngrediente("Asa de morcego");
		p1.adicionaIngrediente("L�ngua de sogra");
		p1.adicionaIngrediente("Ferro Enlatado");
		
		Pizza p2 = new Pizza();
		p2.adicionaIngrediente("L�ngua de sogra");
		p2.adicionaIngrediente("Ferro Enlatado");
		
		Pizza p3 = new Pizza();
		p3.adicionaIngrediente("Asa de morcego");
		p3.adicionaIngrediente("L�ngua de sogra");
		p3.adicionaIngrediente("Ferro Enlatado");
		p3.adicionaIngrediente("C�rebro");
		p3.adicionaIngrediente("Caco de vidro");
		p3.adicionaIngrediente("�gua em p�");
		
		CarrinhoDeCompra cdp = new CarrinhoDeCompra();
		
		/**
		 * Adiciona as pizzas ao carrinho
		 */
		cdp.adicionaPizzaNoCarrinho(p1);
		cdp.adicionaPizzaNoCarrinho(p2);
		cdp.adicionaPizzaNoCarrinho(p3);
		
		/**
		 * Imprime o valor total do carrinho
		 */
		System.out.println("O valor total do carrinho � de: R$ " + cdp.getValorTotalDoCarrinho());
		
		/**
		 * Faz a itera��o no Hashmap dos Ingredientes de pizza e printa para acada ingrediente seu nome e a quantidade de vezes
		 * em que ele foi utilizado
		 */
		for(Entry<String, Integer> entry : Pizza.ingredientes.entrySet()) {
			System.out.println("O ingrediente " + entry.getKey() + " foi utilizado " + entry.getValue() + " vez(es)");
		}
	}
}
