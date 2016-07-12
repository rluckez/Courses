package gameclass;

import impl.EmbaralhadorAleatorio;
import impl.EmbaralhadorInverter;
import impl.EmbaralhadorSeparaImparEPar;
import interfaces.Embaralhador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe utilizada para obter aleatoriamente uma implementação da interface Embaralhador
 * @author Rafael Luckez
 *
 */
public class FabricaEmbaralhadores {

	/**
	 * Retorna aleatoriamente uma instância de embaralhador
	 * @return
	 */
	public static Embaralhador getEmbaralhador(){
		List<Embaralhador> embaralhadores = new ArrayList<>();
		embaralhadores.add(new EmbaralhadorAleatorio());
		embaralhadores.add(new EmbaralhadorInverter());
		embaralhadores.add(new EmbaralhadorSeparaImparEPar());
		return embaralhadores.get(new Random().nextInt(embaralhadores.size()));
	}
	
}
