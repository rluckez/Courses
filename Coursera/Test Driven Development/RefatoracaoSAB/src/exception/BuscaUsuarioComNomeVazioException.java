package exception;

@SuppressWarnings("serial")
public class BuscaUsuarioComNomeVazioException extends Exception {
	public BuscaUsuarioComNomeVazioException(String message)
    {
       super(message);
    }
}
