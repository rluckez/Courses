package source;

public interface ServicoRemoto {

	public ContaCorrente recuperarConta(String numeroDaConta);
	public boolean persistirConta(ContaCorrente contaAPersistir);
	
}
