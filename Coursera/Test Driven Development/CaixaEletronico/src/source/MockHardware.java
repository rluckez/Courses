package source;

public class MockHardware implements Hardware {

	private String numeroDaConta;
	
	public MockHardware(String numeroDaConta) {
		this.setNumeroDaConta(numeroDaConta);
	}
	
	@Override
	public String pegarNumeroDaContaCartao() throws LeituraCartaoException{
		return numeroDaConta;
	}

	@Override
	public void entregaDinheiro() throws EntregaDinheiroExcpetion{

	}

	@Override
	public void lerEnvelope() throws LeituraEnvelopeException{

	}

	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}

}
