import java.util.ArrayList;
import java.util.List;


public class CarrinhoDeCompra {

	public List<Pizza> pizzas = new ArrayList<>();
	
	/**
	 * Adiciona uma pizza ao carrinho se ela possuir ao menos um ingrediente
	 * @param pizza Pizza que será adicionada
	 */
	public void adicionaPizzaNoCarrinho(Pizza pizza){
		if (pizza.qtdIngredientesDaPizza > 0) {
			pizzas.add(pizza);
		}else{
			System.out.println("Essa pizza não possui ingrendientes, portanto, não pode ser adicionado ao carrinho de compras");
		}		
	}
	
	/**
	 * Obtém o valor total do carrinho, contabilizando o preço de cada pizza adicionada nele
	 * @return Valor total do carrinho
	 */
	public int getValorTotalDoCarrinho(){
		int valorTotal = 0;
		for (Pizza p : pizzas) {
			valorTotal += p.getPreco();
		}
		return valorTotal;
	}
	
	
}
