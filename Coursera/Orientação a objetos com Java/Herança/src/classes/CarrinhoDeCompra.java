package classes;

import java.util.HashMap;

public class CarrinhoDeCompra {

	private HashMap<Produto, Integer> produtos = new HashMap<>();
	
	/**
	 * Adiciona produto no carrinho de compra
	 * @param produto Objeto do tipo Produto que será adicionado
	 * @param quantidade do produto a ser inserido
	 */
	public void adicionaProduto(Produto produto, Integer quantidade){
		//Faz a verificação para ver se o produto já existe, se sim, faz a soma 
		//da quantidade já existente com a quantidade a ser inserida
		if (produtos.containsKey(produto)) {
			quantidade += produtos.get(produto);
		}
		//Coloca o produto e sua respectiva quantidade no carrinho
		produtos.put(produto, quantidade);
	}
	
	/**
	 * Remove uma determina quantidade de um determinado produto do carrinho
	 * @param produto Produto que se deseja remover
	 * @param quantidade a ser removida
	 */
	public void removeProduto(Produto produto, Integer quantidade){
		//Verifica se o produto existe no carrinho
		if(produtos.containsKey(produto)){
			//Se existir, verifica se a quantidade a ser removida é maior que a quantidade existente
			if(produtos.get(produto) > quantidade){
				//Se a quantidade a ser removida for menor a quantidade do carrinho, insere no HashMap o produto com sua nova quantidade
				produtos.put(produto, produtos.get(produto) - quantidade);
			}else{
				//Se a quantidade de produtos do carrinho for igual a quantidade a ser removida, remove o produto do carrinho
				if(produtos.get(produto) == quantidade){
					produtos.remove(produto);
				}else{
					System.out.println("Seu carrinho não possui a quantidade de produtos desejada");
				}
			}
		}else{
			System.out.println("Não existe esse produto no carrinho");
		}
	}
	
	/**
	 * Calcula o valor total do carrinho baseado nos produtos nele adicionados
	 * @return Inteiro cotnendo o valor total do carrinho
	 */
	public int calculaValorTotal(){
		//Inicializa o total
		int total = 0;
		//Para cada produto do carrinho adiciona no total a quantidade do produto multiplicada pelo seu preço
		for (Produto produto : produtos.keySet()) {
			total += produto.getPreco() * produtos.get(produto);
		}
		return total;
	}

	//---------------Getters and Setters ---------------------\\
	
	public HashMap<Produto, Integer> getProdutos() {
		return produtos;
	}

	public void setProdutos(HashMap<Produto, Integer> produtos) {
		this.produtos = produtos;
	}
	
}
