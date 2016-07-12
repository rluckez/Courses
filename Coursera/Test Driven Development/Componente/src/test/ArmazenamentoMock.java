package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import exception.PontoInexistentExcpetion;
import exception.QuantidadeDePontosInvalidaExcpetion;
import exception.UsuarioInexistenteExcpetion;
import exception.UsuarioSemPontosException;
import source.Armazenamento;
import source.Curtida;
import source.Estrela;
import source.Moeda;
import source.Ponto;
import source.Usuario;

/**
 * Classe Mock utilizada para simular a classe armazenamento
 * Ela utiliza HashMaps para simular o arquivo de armazenamento onde devem ficar salvas as informações
 * @author Rafael
 *
 */
public class ArmazenamentoMock extends Armazenamento {

	private Usuario guerra, rafael, chico;
	private Map<String, Integer> guerraPoints, rafaelPoints, chicoPoints;
	
	/**
	 * Faz a inicialização dos valores do mock na construção do objeto
	 * @throws IOException
	 * @throws QuantidadeDePontosInvalidaExcpetion
	 */
	public ArmazenamentoMock() throws IOException, QuantidadeDePontosInvalidaExcpetion {
	
		guerra = new Usuario("Guerra");
		rafael = new Usuario("Rafael");
		chico = new Usuario("Chico");
		
		guerraPoints = new HashMap<>();
		rafaelPoints = new HashMap<>();
		chicoPoints = new HashMap<>();
		
		Ponto pontoEstrela = new Estrela();
		Ponto pontoMoeda = new Moeda();
		Ponto pontoCurtida = new Curtida();
		//Guerra
		addPointsToUser(guerra, pontoEstrela, 10);
		addPointsToUser(guerra, pontoMoeda, 5);
		//Rafael		
		addPointsToUser(rafael, pontoCurtida, 15);
		addPointsToUser(rafael, pontoCurtida, 5);
		addPointsToUser(rafael, pontoEstrela, 5);
		//Chico
		addPointsToUser(chico, pontoEstrela, 15);
		addPointsToUser(chico, pontoCurtida, 10);
		addPointsToUser(chico, pontoMoeda, 10);
	}
	/**
	 * Adiciona pontos ao usuário
	 */
	@Override
	public void addPointsToUser(Usuario usuario, Ponto ponto, int quantidade)	throws IOException, QuantidadeDePontosInvalidaExcpetion {
		if(usuario.equals(guerra)){
			if(guerraPoints.containsKey(ponto.getTipo())){
				guerraPoints.put(ponto.getTipo(), guerraPoints.get(ponto.getTipo() + quantidade));
			}else{
				guerraPoints.put(ponto.getTipo(), quantidade);
			}
			
			return;
		}
		if(usuario.equals(rafael)){
			if(rafaelPoints.containsKey(ponto.getTipo())){
				rafaelPoints.put(ponto.getTipo(), rafaelPoints.get(ponto.getTipo() + quantidade));
			}else{
				rafaelPoints.put(ponto.getTipo(), quantidade);
			}
			return;
		}
		if(usuario.equals(chico)){
			if(chicoPoints.containsKey(ponto.getTipo())){
				chicoPoints.put(ponto.getTipo(), chicoPoints.get(ponto.getTipo() + quantidade));
			}else{
				chicoPoints.put(ponto.getTipo(), quantidade);
			}
			return;
		}
		throw new RuntimeException();
	}
	
	/**
	 * Recupera os usuários com o tipo de ponto
	 */
	@Override
	public List<Usuario> getUsersWithThatPoint(Ponto ponto) throws IOException {
		List<Usuario> users = new ArrayList<>();
		if(guerraPoints.containsKey(ponto.getTipo())){
			users.add(guerra);
		}
		if(rafaelPoints.containsKey(ponto.getTipo())){
			users.add(rafael);
		}
		if(chicoPoints.containsKey(ponto.getTipo())){
			users.add(chico);
		}
		return users;
	}
	
	/**
	 * Recupera todos tipos de pontos do usuário
	 */
	@Override
	public List<String> getAllPointsTypeFromUser(Usuario usuario) throws UsuarioInexistenteExcpetion, IOException, UsuarioSemPontosException {
		List<String> pointsFromUser = new ArrayList<>();
		if(usuario.equals(guerra)){
			for (Entry<String, Integer> entry : guerraPoints.entrySet()) {
				pointsFromUser.add(entry.getKey());
			}
			return pointsFromUser;
		}
		if(usuario.equals(rafael)){
			for (Entry<String, Integer> entry : rafaelPoints.entrySet()) {
				pointsFromUser.add(entry.getKey());
			}
			return pointsFromUser;
		}
		if(usuario.equals(chico)){
			for (Entry<String, Integer> entry : chicoPoints.entrySet()) {
				pointsFromUser.add(entry.getKey());
			}
			return pointsFromUser;
		}
		throw new UsuarioInexistenteExcpetion("Usuário inexistente");
	}
	
	/**
	 * Recupera os pontos de um usuário
	 */
	@Override
	public int getUserPoints(Usuario usuario, Ponto ponto) throws IOException, PontoInexistentExcpetion {
		if(usuario.equals(guerra)){
			return guerraPoints.get(ponto.getTipo());
		}
		if(usuario.equals(rafael)){
			return rafaelPoints.get(ponto.getTipo());
		}
		if(usuario.equals(chico)){
			return chicoPoints.get(ponto.getTipo());
		}
		throw new IOException();
	}
}
