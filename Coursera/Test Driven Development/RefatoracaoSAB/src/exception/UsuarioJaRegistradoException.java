package exception;

@SuppressWarnings("serial")
public class UsuarioJaRegistradoException extends Exception {
	public UsuarioJaRegistradoException(String message)
    {
       super(message);
    }
}
