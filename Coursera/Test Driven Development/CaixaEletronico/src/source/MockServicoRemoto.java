package source;

public class MockServicoRemoto implements ServicoRemoto {

	private ContaCorrente contaCorrente;
	
	public MockServicoRemoto(ContaCorrente cc){
		this.contaCorrente = cc;
	}
	
	@Override
	public ContaCorrente recuperarConta(String numeroDaConta) {
		if(this.contaCorrente.getNumeroDaConta().equals(numeroDaConta)){
			return contaCorrente;
		}
		return null;
	}

	@Override
	public boolean persistirConta(ContaCorrente contaAPersistir) {

		return true;
	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

}
