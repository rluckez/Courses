package source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import exception.PontoInexistentExcpetion;
import exception.QuantidadeDePontosInvalidaExcpetion;
import exception.UsuarioInexistenteExcpetion;
import exception.UsuarioSemPontosException;

public class Placar {

	private Armazenamento armazenamento;
	
	public Placar(Armazenamento armazenamento){
		this.armazenamento = armazenamento;
	}

	/**
	 * Adiciona pontos a um usuário
	 * @param usuario usuário desejado
	 * @param ponto Tipo do ponto
	 * @param quantidade Quantidade de pontos que se deseja adicionar
	 * @return
	 */
	public boolean addPointsToUser(Usuario usuario, Ponto ponto, int quantidade) {
		try {
			armazenamento.addPointsToUser(usuario, ponto, quantidade);
			return true;
		} catch (IOException | QuantidadeDePontosInvalidaExcpetion e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Recupera todos os pontos de um usuário
	 * @param usuario Usuário desejado
	 * @return String contendo todos os pontos do usuário
	 */
	public String getAllPointsFromUser(Usuario usuario){
		try {
			List<String> pointsType = armazenamento.getAllPointsTypeFromUser(usuario);
			Map<String, Integer> pointsFromUser = new HashMap<>();
			for (String point : pointsType) {
				pointsFromUser.put(point, armazenamento.getUserPoints(usuario, Ponto.builder(point)));
			}
			String result = "";
			result += "O usuário " + usuario.getName() + " possui os seguintes pontos: ";
			for (Map.Entry<String, Integer> entry : pointsFromUser.entrySet()) {
				result += entry.getValue();
			    result += " ";
			    result += entry.getKey();			    
			    result += " - "; 
			}
			
			result = result.substring(0, result.length() - 3);
			result += ".";
			return result;
			
		} catch (UsuarioInexistenteExcpetion | IOException | UsuarioSemPontosException | PontoInexistentExcpetion e) {
			e.printStackTrace();
			return "Problema ao obter a pontuação do usuário";
		}
	}
	
	/**
	 * Obtém o ranking dos usuários baseado em algum ponto específico 
	 * @param ponto que se deseja basear o ranking
	 * @return String informando o ranking dos usuários
	 */
	public String getUserRankingByPointType(Ponto ponto){
		try {
			//Recupera os usuários que possuem o ponto desejado
			List<Usuario> allUsersWithThatPoint = armazenamento.getUsersWithThatPoint(ponto);
			//Armazena os pontos dos usuários num Map
			Map<String, Integer> usersWithPoints = new HashMap<>();
			for (Usuario usuario : allUsersWithThatPoint) {
				usersWithPoints.put(usuario.getName(), armazenamento.getUserPoints(usuario, ponto));
			}
			
			//Faz a ordenação crescente através de um LinkedHashMap
			Map<String, Integer> sortedMap = usersWithPoints.entrySet().stream().sorted(Entry.comparingByValue())
					.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			String result = "O ranking de usuários baseado no ponto ";
			result += ponto.getTipo();
			result += " é:";
			
			List<Entry<String,Integer>> list = new ArrayList<Entry<String,Integer>>(sortedMap.entrySet());
			//Navega de trás para frente no LinkedHashMap criando o ranking na ordem decrescente
			for (int i = list.size() - 1; i >= 0; i--) {
			    Entry<String,Integer> entry = list.get(i);
			    result+= " " + entry.getKey() + " - " + entry.getValue() + " Pontos."; 
			}
			
			return result;
		} catch (IOException | PontoInexistentExcpetion e ) {
			e.printStackTrace();
			return "Problema ao obter ranking de usuários";
		}
	}
	
}
