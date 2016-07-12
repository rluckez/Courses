package exception;

public class PalavraInvalidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public PalavraInvalidaException(){
		this.message = "A palavra obtida n�o pode ser embaralhada";
	}
	
	public String getMessage(){
		return message;
	}

}
