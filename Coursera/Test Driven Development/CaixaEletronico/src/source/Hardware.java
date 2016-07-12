package source;

public interface Hardware {

	public String pegarNumeroDaContaCartao() throws LeituraCartaoException;
	public void entregaDinheiro() throws EntregaDinheiroExcpetion;
	public void lerEnvelope() throws LeituraEnvelopeException;
	
}
