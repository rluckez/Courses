package gameclass;

import impl.MecanicaDaQuantidade;
import impl.MecanicaDosErros;
import impl.MecanicaErrouPerdeu;
import interfaces.MecanicaDoJogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe utilizada para obter aleatoriamente uma implementação da classe abstrata MecanicaDoJogo
 * @author Rafael Luckez
 *
 */
public class FabricaMecanicaDoJogo {

	/**
	 * Retorna aleatoriamente uma implementação de MecanicaDoJogo
	 * @return
	 */
	public static MecanicaDoJogo getMecanicaDoJogo(){
		List<MecanicaDoJogo> mecanicas = new ArrayList<>();
		mecanicas.add(new MecanicaDaQuantidade());
		mecanicas.add(new MecanicaDosErros());
		mecanicas.add(new MecanicaErrouPerdeu());
		return mecanicas.get(new Random().nextInt(mecanicas.size()));
	}
	
}
