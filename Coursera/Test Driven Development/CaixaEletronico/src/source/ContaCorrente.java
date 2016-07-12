package source;

public class ContaCorrente {

	//TODO
	//Criar senha e método autentica
	private int saldo;
	private String numeroDaConta;
	private String senha;
	
	public ContaCorrente(String numero, String senha){
		this.numeroDaConta = numero;
		this.senha = senha;
	}
	
	public boolean autentica(String senha) throws SenhaInvalidaException{
		if(this.senha.equals(senha))
			return true;
		throw new SenhaInvalidaException();
	}
	
	public int getSaldo() {
		return saldo;
	}

	public boolean depositar(int valor) {
		this.saldo += valor;
		return true;
	}

	public boolean sacar(int valor) throws SaldoInsuficienteException {
		if(saldo < valor){
			throw new SaldoInsuficienteException();
		}
		this.saldo -= valor;
		return true;
	}

	public String getNumeroDaConta() {
		return numeroDaConta;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
