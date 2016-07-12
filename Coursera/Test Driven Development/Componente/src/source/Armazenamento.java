package source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import exception.PontoInexistentExcpetion;
import exception.QuantidadeDePontosInvalidaExcpetion;
import exception.UsuarioInexistenteExcpetion;
import exception.UsuarioSemPontosException;

public class Armazenamento {

	public static final String CONTROL_FILE_NAME = "Controle";
	private static final String FIELD_SEPARATOR = ";";
	
	public Armazenamento(){
		try {
			createControlFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cria o arquivo de texto que é utilizado para armazenador os dados
	 * @return Objeto File que representa o arquivo criado
	 * @throws IOException
	 */
	public File createControlFile() throws IOException {
		File file = new File(getControlFilePath());
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		return file;
	}
	
	/**
	 * Retorna o path onde se encontra o arquivo de armazenamento
	 * @return String contendo o path
	 */
	private String getControlFilePath(){
		Path currentRelativePath = Paths.get("");
		String filePath = currentRelativePath.toAbsolutePath().toString();
		filePath += File.separatorChar + "src" + File.separatorChar + "source" + File.separatorChar + CONTROL_FILE_NAME + ".txt";
		return filePath;
	}

	/**
	 * Realiza a leitura do arquivo de armazenamento e retorna seu conteúdo como lista de Strings
	 */
	public List<String> readControlFile() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(getControlFilePath()));
		return lines;
	}

	/**
	 * Adiciona um conteúdo no arquivo de armazenamento
	 * @param content
	 * @throws IOException
	 */
	public void addContentOnFile(String content) throws IOException {
		String filePath = getControlFilePath();
		List<String> lines = Files.readAllLines(Paths.get(filePath));
		lines.add(content);
		Files.write(Paths.get(filePath), lines);
	}

	/**
	 * Escreve toda a lista recebida como parâmetro como arquivo .txt, sendo cada posição do array uma linha no arquivo
	 * @param lines
	 * @throws IOException
	 */
	private void writeToFile(List<String> lines) throws IOException{
		Files.write(Paths.get(getControlFilePath()), lines);
	}
	
	/**
	 * Guarda um usuário no arquivo de armazenamento
	 * @param usuario
	 * @throws IOException
	 */
	public void storeUser(Usuario usuario) throws IOException {
		addContentOnFile(usuario.getName());
	}

	/**
	 * Adiciona pontos a um usuário
	 * @param usuario Usuário a receber os pontos
	 * @param ponto Tipo do ponto
	 * @param quantidade Quantidade de pontos
	 * @throws IOException
	 * @throws QuantidadeDePontosInvalidaExcpetion
	 */
	public void addPointsToUser(Usuario usuario, Ponto ponto, int quantidade) throws IOException, QuantidadeDePontosInvalidaExcpetion {
		if(quantidade <= 0 ){
			throw new QuantidadeDePontosInvalidaExcpetion("Quantidade de pontos inválida");
		}
		List<String> lines = readControlFile();
		
		if(checkUserExist(usuario, lines)){
			lines = changeUserEntry(usuario, ponto, quantidade, lines);
			writeToFile(lines);
		}else{
			StringBuilder contentToWrite = new StringBuilder();
			contentToWrite.append(usuario.getName());
			contentToWrite.append(FIELD_SEPARATOR);
			contentToWrite.append(ponto.getTipo());
			contentToWrite.append("=");
			contentToWrite.append(quantidade);
			addContentOnFile(contentToWrite.toString());
		}
	}
	
	/**
	 * Atualiza o registro de um usuário
	 * @param usuario Usuário desejado
	 * @param ponto TIpo do ponto
	 * @param quantidade Quantidade
	 * @param lines Lista com as Strings que representam o arquivo de armazenamento
	 * @return Array com o valor do usuário informado atualizado
	 * @throws IOException
	 */
	private List<String> changeUserEntry(Usuario usuario, Ponto ponto, int quantidade, List<String> lines) throws IOException {
		String entryToChange = "";
		for (String line : lines) {
			if(line.contains(usuario.getName())){
				entryToChange = addPointToUser(line, ponto, quantidade);
				lines.set(lines.indexOf(line), entryToChange);
			}
		}
		return lines;
	}

	
	private String addPointToUser(String line, Ponto ponto, int quantidade) {
		if(userHasThatPoint(line, ponto)){
			String[] lineContent = line.split(";");
			for (String content : lineContent) {
				if (content.contains(ponto.getTipo())) {
					int currentPoints = Integer.parseInt(content.split("=")[1]);
					String newContent = content.replace(String.valueOf(currentPoints), (currentPoints + quantidade) + "");
					line = line.replace(content, newContent);
				}
			}
		}else{
			line += FIELD_SEPARATOR + ponto.getTipo() + "=" + quantidade;
		}
		return line;
	}

	/**
	 * Verifica se o usuário possui o tipo de ponto informado
	 * @param content String que representa o registro do usuário no arquivo de armazenamento
	 * @param ponto Tipo do ponto que se deseja verificar
	 * @return
	 */
	private boolean userHasThatPoint(String content, Ponto ponto) {
		if(content.contains(ponto.getTipo()))
			return true;
		return false;
	}

	/**
	 * Verifica se o usuário já existe no arquivo de armazenamento
	 * @param usuario Usuário que se deseja verificar
	 * @param lines Conteúdo do arquivo de armazenamento
	 * @return 
	 */
	private boolean checkUserExist(Usuario usuario, List<String> lines){
		for (String line : lines) {
			if(line.contains(usuario.getName()))
				return true;
		}
		return false;
	}

	/**
	 * Retorna a quantidade de um determinado ponto de um determinado usuário
	 * @param usuario Usuário desejado
	 * @param ponto Tipo do ponto que se deseja obter a quantidade
	 * @return
	 * @throws IOException
	 * @throws PontoInexistentExcpetion
	 */
	public int getUserPoints(Usuario usuario, Ponto ponto) throws IOException, PontoInexistentExcpetion {
		List<String> lines = readControlFile();
		if(checkUserExist(usuario, lines)){
			for (String line : lines) {
				if(line.contains(usuario.getName())){
					String[] lineContent = line.split(";");
					for (String point : lineContent) {
						if(point.contains(ponto.getTipo())){
							return Integer.parseInt(point.split("=")[1]);
						}
					}
				}
			}
		}
		throw new PontoInexistentExcpetion("Ponto Inexistente para o usuário " + usuario.getName() + ".");
	}

	/**
	 * Busca todos usuários que possuem determinado tipo de ponto
	 * @param ponto Ponto que se deseja verificar
	 * @return
	 * @throws IOException
	 */
	public List<Usuario> getUsersWithThatPoint(Ponto ponto) throws IOException {
		List<String> lines = readControlFile();
		List<Usuario> usersWithThatPoint = new ArrayList<>();
		for (String line : lines) {
			if(line.contains(ponto.getTipo())){
				usersWithThatPoint.add(new Usuario(line.split(";")[0]));
			}
		}
		return usersWithThatPoint;
	}

	/**
	 * Retorna todos os tipos de pontos do usuário
	 * @param usuario Usuário desejado
	 * @return
	 * @throws UsuarioInexistenteExcpetion
	 * @throws IOException
	 * @throws UsuarioSemPontosException
	 */
	public List<String> getAllPointsTypeFromUser(Usuario usuario) throws UsuarioInexistenteExcpetion, IOException, UsuarioSemPontosException {
		List<String> lines = readControlFile();
		if(checkUserExist(usuario, lines)){
			List<String> typesOfPoint = new ArrayList<>();
			for (String line : lines) {
				if(line.contains(usuario.getName())){
					String[] lineContent = line.split(";");
					if(lineContent.length > 1){
						for(int i = 1; i < lineContent.length; i++){
							typesOfPoint.add(lineContent[i].split("=")[0]);
						}
						return typesOfPoint;
					}
					throw new UsuarioSemPontosException("Este usuário não possui pontos");
				}
			}
		}
		throw new UsuarioInexistenteExcpetion("Este usuário não existe");	
	}

}
