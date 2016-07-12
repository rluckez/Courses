package source;

public class HardwareQueFalha implements Hardware {

	@Override
	public String pegarNumeroDaContaCartao() throws LeituraCartaoException {
		throw new LeituraCartaoException();
	}

	@Override
	public void entregaDinheiro() throws EntregaDinheiroExcpetion {
		 throw new EntregaDinheiroExcpetion();
	}

	@Override
	public void lerEnvelope() throws LeituraEnvelopeException {
		throw new LeituraEnvelopeException();
	}

}
