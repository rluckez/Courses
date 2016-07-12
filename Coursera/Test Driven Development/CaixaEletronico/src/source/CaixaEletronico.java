package source;

public class CaixaEletronico {

	private ContaCorrente contaCorrente;
	private ServicoRemoto servicoRemoto;
	private Hardware hardware;
	
	public String saldo() {
		return "O saldo é R$ " + String.format("%.2f", Integer.valueOf(contaCorrente.getSaldo()).doubleValue());
	}

	public String sacar(int valor) {
		try {
			contaCorrente.sacar(valor);
			hardware.entregaDinheiro();
			servicoRemoto.persistirConta(contaCorrente);
			return "Retire seu dinheiro";
		} catch (SaldoInsuficienteException e) {
			e.printStackTrace();
			return "Saldo insuficiente";
		} catch (EntregaDinheiroExcpetion e) {
			e.printStackTrace();
			return "Problema ao entregar dinheiro";
		}
		
	}

	public String depositar(int valor) {
		try {
			hardware.lerEnvelope();
			contaCorrente.depositar(valor);
			servicoRemoto.persistirConta(contaCorrente);
			return "Depósito recebido com sucesso";
		} catch (LeituraEnvelopeException e) {
			e.printStackTrace();
			return "Falha na leitura do envelope"; 
		}
	}
	
	public String logar(String senha){
		try {
			String numeroDaConta = hardware.pegarNumeroDaContaCartao();
			contaCorrente = servicoRemoto.recuperarConta(numeroDaConta);
			contaCorrente.autentica(senha);
		} catch (LeituraCartaoException | SenhaInvalidaException e ) {
			e.printStackTrace();
			return "Não foi possível autenticar o usuário";
		}
		return "Usuário Autenticado";
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}

	public void setServicoRemoto(ServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
	}

}
