import java.util.HashMap;
import java.util.Map;



public class Pizza {

	public static Map<String, Integer> ingredientes = new HashMap<>(); 
	
	public int qtdIngredientesDaPizza = 0;
	
	public void adicionaIngrediente(String ingrediente){
		qtdIngredientesDaPizza++;
		contabilizaIngrediente(ingrediente);
	}
	
	/**
	 * Calcula o preço da Pizza
	 * @return Valor da pizza de acordo com as regras especificadas
	 */
	public Integer getPreco(){
		if(qtdIngredientesDaPizza <= 2){
			return 15;
		}else if(qtdIngredientesDaPizza <= 5){
			return 20;
		}else{
			return 23;
		}
	}
	
	/**
	 * Adiciona o ingrediente no map de ingrendiente e faz a contagem do ingrediente adicionado no map
	 * @param ingrediente
	 */
	public static void contabilizaIngrediente(String ingrediente){
		if (ingredientes.containsKey(ingrediente)) {
			ingredientes.put(ingrediente, ingredientes.get(ingrediente) + 1);
		}else{
			ingredientes.put(ingrediente, 1);
		}
		
	}
	
}
