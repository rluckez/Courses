package exception;

@SuppressWarnings("serial")
public class UsuarioInexistenteExcpetion extends Exception {

	public UsuarioInexistenteExcpetion(String message){
		super(message);
	}
}
